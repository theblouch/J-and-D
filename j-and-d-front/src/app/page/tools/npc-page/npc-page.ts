import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { NPCDto } from '../../../dto/npc-dto';
import { NPCService } from '../../../service/npc-service';

@Component({
  selector: 'app-npc',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './npc-page.html',
  styleUrls: ['./npc-page.css'],
})
export class NpcPage implements OnInit {

  npcForm!: FormGroup;
  npcs$!: Observable<NPCDto[]>;

  showForm = false;
  editingNpc: NPCDto | null = null;

  // session ID stockée
  sessionId: string | null = null;

  constructor(
    private formBuilder: FormBuilder,
    private npcService: NPCService,
  ) {}

  ngOnInit(): void {

    // Récupère la session active (déjà stockée ailleurs)
    this.sessionId = sessionStorage.getItem("sessionId");

    if (this.sessionId) {
      this.npcs$ = this.npcService.findAll();
    }

    this.npcForm = this.formBuilder.group({
      name: ['', Validators.required],
      level: [1, Validators.required],
      hp: [1, Validators.required],
      mp: [0],
      speed: [1],
      armorClass: [0],
      initiative: [0],
      alive: [true],
    });
  }

  public creer(): void {
    if (!this.sessionId) return;

    const f = this.npcForm.value;

    const npc = new NPCDto(
      0,
      f.name,
      f.level,
      f.hp,
      f.mp,
      f.speed,
      f.alive,
      f.armorClass,
      f.initiative,

      null,     // armor
      null,     // weapon
      [],       // itemWorn
      [],       // inventory
      null,     // stats
      null,     // role
      [],       // state
      null,     // tauntedBy
      0,        // xp

      { id: Number(this.sessionId) } // session minimale
    );

    this.npcService.save(npc);

    this.showForm = false;
    this.editingNpc = null;
    this.npcForm.reset();
  }

  public editer(npc: NPCDto): void {
    this.editingNpc = npc;
    this.showForm = true;

    this.npcForm.patchValue({
      name: npc.name,
      level: npc.level,
      hp: npc.hp,
      mp: npc.mp,
      speed: npc.speed,
      armorClass: npc.armorClass,
      initiative: npc.initiative,
      alive: npc.alive,
    });
  }

  public annuler(): void {
    this.showForm = false;
    this.editingNpc = null;
    this.npcForm.reset();
  }

  public delete(id: number): void {
    this.npcService.deleteById(id);
  }

  public displayBoolean(val: boolean): string {
    return val ? "Oui" : "Non";
  }
}
