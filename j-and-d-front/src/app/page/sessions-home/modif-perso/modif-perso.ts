import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormArray, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Location, CommonModule } from '@angular/common';
import { CharacterService } from '../../../service/character-service';
import { RoleService } from '../../../service/role-service';
import { ItemService } from '../../../service/item-service';
import { ItemDto } from '../../../dto/item-dto';
import { ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-modif-perso',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './modif-perso.html',
  styleUrls: ['./modif-perso.css']
})
export class ModifPerso implements OnInit {

  characterForm!: FormGroup;
  roles: any[] = [];
  allItems: ItemDto[] = [];
  errorMessage: string = "";

  constructor(
    private fb: FormBuilder,
    private characterService: CharacterService,
    private roleService: RoleService,
    private itemService: ItemService,
    private route: ActivatedRoute,
    private location: Location
  ) { }

  ngOnInit(): void {
    this.loadRoles();
    this.loadItems();
    this.loadCharacter();
  }

  loadRoles(): void {
    this.roleService.getAll().subscribe({
      next: data => this.roles = data,
      error: err => console.error(err)
    });
  }

  loadItems(): void {
    this.itemService.findAll().subscribe(items => this.allItems = items);
  }

  loadCharacter(): void {
    const id = Number(this.route.snapshot.paramMap.get('persoId'));
    this.characterService.get(id).subscribe({
      next: data => this.initForm(data),
      error: err => {
        console.error(err);
        this.errorMessage = "Erreur lors du chargement du personnage.";
      }
    });
  }

  initForm(character: any): void {
    this.characterForm = this.fb.group({
      id: [character.id],
      name: [character.name, Validators.required],
      level: [character.level, [Validators.required, Validators.min(1)]],
      race: [character.race],
      roleId: [character.roleId],
      alive: [character.alive],
      hp: [character.hp],
      mp: [character.mp],
      speed: [character.speed],
      armorClass: [character.armorClass],
      initiative: [character.initiative],
      stats: this.fb.group(this.createStatsGroup(character.stats)),
      armorId: [character.armorId],
      weaponId: [character.weaponId],
      itemWornIds: this.fb.array(character.itemWornIds || []),
      inventoryIds: this.fb.array(character.inventoryIds || [])
    });
  }

  createStatsGroup(stats: any): { [key: string]: any } {
    const group: { [key: string]: any } = {};
    for (const key of Object.keys(stats)) {
      group[key] = [stats[key]];
    }
    return group;
  }

  get itemWornArray(): FormArray {
    return this.characterForm.get('itemWornIds') as FormArray;
  }

  get inventoryArray(): FormArray {
    return this.characterForm.get('inventoryIds') as FormArray;
  }

  get statsControls(): { [key: string]: any } {
    return (this.characterForm.get('stats') as FormGroup).controls;
  }

  addItemWorn(): void {
    this.itemWornArray.push(this.fb.control(null));
  }

  addInventoryItem(): void {
    this.inventoryArray.push(this.fb.control(null));
  }

  removeItemWorn(index: number): void {
    this.itemWornArray.removeAt(index);
  }

  removeInventoryItem(index: number): void {
    this.inventoryArray.removeAt(index);
  }

  saveCharacter(): void {
    if (this.characterForm.invalid) {
      this.errorMessage = "Certains champs sont invalides.";
      return;
    }

    this.characterService.update(this.characterForm.value).subscribe({
      next: () => this.location.back(),
      error: err => {
        console.error(err);
        this.errorMessage = "Erreur lors de la sauvegarde du personnage.";
      }
    });
  }

  goBack(): void {
    this.location.back();
  }
}
