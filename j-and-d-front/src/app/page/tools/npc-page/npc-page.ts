import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { NPCDto } from '../../../dto/npc-dto';
import { NPCService } from '../../../service/npc-service';

@Component({
  selector: 'app-npc',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './npc-page.html',
  styleUrls: ['./npc-page.css'],
})
export class NpcPage implements OnInit {

  npcForm!: FormGroup;
  npcs$!: Observable<NPCDto[]>;
  showForm = false;
  editingNpc: NPCDto | null = null;

  constructor(
    private formBuilder: FormBuilder,
    private npcService: NPCService,
  ) {}

  ngOnInit(): void {

    this.npcs$ = this.npcService.findAll();

    this.npcForm = this.formBuilder.group({
      name: ['', Validators.required],
      level: [1, Validators.required],
      hp: [1, Validators.required],
      mp: [0],
      speed: [1],
      armorClass: [0],
      initiative: [0],
      alive: [true],

      armor: [''],       // string → envoyé tel quel
      weapon: [''],      // string
      itemWorn: [''],    // liste séparée par virgules
      inventory: [''],   // liste séparée par virgules

      statsStr: ['', Validators.required],  // ex: "10,10,10,10,10,10"

      roleName: ['', Validators.required],

      state: [''],        // ex: "poison,slow"

      xp: [0],
    });
  }

  public creer(): void {
    const f = this.npcForm.value;

    const statsArray = f.statsStr.split(',').map((x: string) => Number(x.trim()));

    const stats = {
      strength: statsArray[0] ?? 0,
      dexterity: statsArray[1] ?? 0,
      constitution: statsArray[2] ?? 0,
      intelligence: statsArray[3] ?? 0,
      wisdom: statsArray[4] ?? 0,
      charisma: statsArray[5] ?? 0,
    };

    const itemWorn = f.itemWorn ? f.itemWorn.split(',').map((x: string) => x.trim()) : [];
    const inventory = f.inventory ? f.inventory.split(',').map((x: string) => x.trim()) : [];
    const state = f.state ? f.state.split(',').map((x: string) => x.trim()) : [];

    const role = {
      id: 0,
      name: f.roleName
    };

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

      f.armor || null,
      f.weapon || null,
      itemWorn,
      inventory,
      stats,
      role,
      state,
      null,
      f.xp,
      null
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

      armor: npc.armor || "",
      weapon: npc.weapon || "",
      itemWorn: npc.itemWorn?.join(', ') ?? "",
      inventory: npc.inventory?.join(', ') ?? "",
      statsStr: Object.values(npc.stats).join(','),
      roleName: npc.role?.name ?? "",
      state: npc.state?.join(',') ?? "",
      xp: npc.xP,
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

  public displayBoolean(v: boolean): string {
    return v ? "Oui" : "Non";
  }
}
