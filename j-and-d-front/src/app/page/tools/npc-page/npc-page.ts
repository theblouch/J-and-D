import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Observable } from 'rxjs';

import { NPCDto } from '../../../dto/npc-dto';
import { NPCService } from '../../../service/npc-service';
import { ItemService } from '../../../service/item-service';
import { SessionService } from '../../../service/session-service';
import { ItemDto } from '../../../dto/item-dto';
import { SessionDto } from '../../../dto/session-dto';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-npc',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './npc-page.html',
  styleUrl: './npc-page.css'
})
export class NPCPage implements OnInit {

  npcs$!: Observable<NPCDto[]>;
  npcForm!: FormGroup;

  items$!: Observable<ItemDto[]>;
  sessions$!: Observable<SessionDto[]>;

  editingNpc: NPCDto | null = null;
  showForm: boolean = false;

  // constantes Monster
  readonly MONSTER_ROLE_ID = 5;
  readonly DEFAULT_ARMOR_ID = 10;
  readonly DEFAULT_WEAPON_ID = 9;

  readonly MONSTER_STATS = {
    charisma: 6,
    constitution: 12,
    dexterity: 12,
    intelligence: 6,
    strength: 13,
    wisdom: 8
  };

  constructor(
    private fb: FormBuilder,
    private npcService: NPCService,
    private itemService: ItemService,
    private sessionService: SessionService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.npcs$ = this.npcService.findAll();
    this.items$ = this.itemService.findAll();
    this.sessions$ = this.sessionService.findAll();

    this.npcForm = this.fb.group({
      name: ['', Validators.required],
      level: [1, [Validators.required, Validators.min(1)]],

      hp: [8, Validators.required],
      mp: [0, Validators.required],
      speed: [5.0, Validators.required],
      alive: [true],
      armorClass: [10, Validators.required],
      initiative: [10, Validators.required],
      xP: [0],

      armorId: [this.DEFAULT_ARMOR_ID, Validators.required],
      weaponId: [this.DEFAULT_WEAPON_ID, Validators.required],

      itemWornIds: [[]],
      inventoryIds: [[]],

      sessionId: [null]
    });
  }
  goHome() {
    this.router.navigate(['/tools']);
  }
  /* -------------------- CRÉER / MODIFIER -------------------- */
  public creer(): void {
    const f = this.npcForm.value;

    const npc = new NPCDto(
      this.editingNpc ? this.editingNpc.id : 0,
      f.name,
      f.level,

      f.hp,
      f.mp,
      f.speed,
      f.alive,
      f.armorClass,
      f.initiative,

      { id: f.armorId },
      { id: f.weaponId },

      (f.itemWornIds || []).map((id: number) => ({ id })),
      (f.inventoryIds || []).map((id: number) => ({ id })),

      this.MONSTER_STATS,
      { id: this.MONSTER_ROLE_ID },

      [],          // state
      null,        // tauntedBy
      f.xP,
      f.sessionId ? { id: f.sessionId } : null
    );
  }
  /* -------------------- EDITER -------------------- */
  public editer(npc: NPCDto): void {
    this.editingNpc = npc;
    this.showForm = true;

    this.npcForm.patchValue({
      name: npc.name,
      level: npc.level,
      hp: npc.hp,
      mp: npc.mp,
      speed: npc.speed,
      alive: npc.alive,
      armorClass: npc.armorClass,
      initiative: npc.initiative,
      xP: npc.xP,

      armorId: npc.armor?.id ?? this.DEFAULT_ARMOR_ID,
      weaponId: npc.weapon?.id ?? this.DEFAULT_WEAPON_ID,

      itemWornIds: npc.itemWorn?.map((x: any) => x.id) ?? [],
      inventoryIds: npc.inventory?.map((x: any) => x.id) ?? [],

      sessionId: npc.session?.id ?? null
    });
  }

  public annulerEditer(): void {
    this.showForm = false;
    this.editingNpc = null;
    this.npcForm.reset();
  }

  /* -------------------- SUPPRIMER -------------------- */
  public delete(id: number): void {
    this.npcService.deleteById(id);
  }
}
