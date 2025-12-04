import { Component } from '@angular/core';
import { CharacterService } from '../../../service/character-service';
import { ActivatedRoute } from '@angular/router';
import { RoleService } from '../../../service/role-service';
import { CommonModule, Location } from '@angular/common';
import { ItemService } from '../../../service/item-service';
import { ItemDto } from '../../../dto/item-dto';

@Component({
  selector: 'app-character-detail',
  imports: [CommonModule],
  templateUrl: './character-detail.html',
  styleUrl: './character-detail.css',
})
export class CharacterDetail {

  character: any = [];
  errorMessage: string = "";
  roles: any[] = [];
  allItems: ItemDto[] = [];

  constructor(private location: Location, private characterService: CharacterService, private roleService: RoleService, private route: ActivatedRoute, private itemService: ItemService) { }

  ngOnInit(): void {
    this.loadCharacter();
    this.loadRoles();
    this.loadItems();
  }

  loadItems(): void {
    this.itemService.findAll().subscribe(items => {
      this.allItems = items;
    });
  }

  loadCharacter(): void {
    this.characterService.get(Number(this.route.snapshot.paramMap.get('id'))).subscribe({
      next: data => {
        this.character = data;
      },
      error: err => {
        console.error(err);
        this.errorMessage = 'Erreur lors du chargement des personnages.';
      }
    });
  }

  getStatsKeys(stats: any): string[] {
    return Object.keys(stats);
  }

  loadRoles(): void {
    this.roleService.getAll().subscribe({
      next: data => {
        this.roles = data;
      },
      error: err => console.error(err)
    });
  }



  getRoleName(roleId: number): string {
    console.log('Roles disponibles:', this.roles); // Debug
    console.log('Cherche roleId:', roleId);
    const role = this.roles.find(r => r.id === roleId);
    return role ? role.name : 'Inconnu';
  }


  getItemName(itemId: number): string {
    const item = this.allItems.find(i => i.id === itemId);
    return item?.name || `Item #${itemId}`;
  }

  getArmorName(armorId: number): string {
    const armor = this.allItems.find(i => i.id === armorId);
    return armor?.name || `Armure #${armorId}`;
  }

  getWeaponName(weaponId: number): string {
    const weapon = this.allItems.find(i => i.id === weaponId);
    return weapon?.name || `Arme #${weaponId}`;
  }

  goBack(): void {
    this.location.back();
  }
}


