import { Component } from '@angular/core';
import { CharacterService } from '../../../service/character-service';
import { RoleService } from '../../../service/role-service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RaceService } from '../../../service/race-service';

@Component({
  selector: 'app-create-character-page',
  imports: [FormsModule, CommonModule],
  templateUrl: './create-character-page.html',
  styleUrl: './create-character-page.css',
})
export class CreateCharacterPage {
  roles: any[] = [];
  races: any[] = [];
  selectedRole: any | null = null;
  selectedRoleId: number | null = null;
  selectedRace: string | null = null;

  successMessage: string = '';

  character: any = {
    name: '',
    roleId: null,
    stats: null,
    race: 'HUMAN'
  };

  constructor(
    private characterService: CharacterService,
    private roleService: RoleService,
    private raceService: RaceService
  ) { }

  ngOnInit(): void {
    this.loadRoles();
    this.loadRaces();
  }

  loadRoles(): void {
    this.roleService.getAll().subscribe({
      next: data => {
        this.roles = data;
      },
      error: err => console.error(err)
    });
  }

  loadRaces(): void {
    this.raceService.getAll().subscribe({
      next: (data: any) => {
        this.races = data;
      },
      error: err => console.error(err)
    });
  }

  onRoleChange(): void {
    this.selectedRole = this.roles.find(r => r.id == this.selectedRoleId);

    if (this.selectedRole) {
      // ========== STATS PAR DÉFAUT ==========
      this.character.roleId = this.selectedRole.id;
      this.character.stats = { ...this.selectedRole.baseStats };
      // (copie profonde pour éviter que l'utilisateur modifie les stats du rôle)
    }
  }


  onRaceChange(race: any): void {
    this.selectedRace = race;

    this.character.race = race;
  }

  createCharacter(): void {
    this.characterService.create(this.character).subscribe({
      next: created => {
        this.successMessage = `Personnage "${this.character.name}" créé avec succès !`;
      },
      error: err => console.error(err)
    });
  }
}