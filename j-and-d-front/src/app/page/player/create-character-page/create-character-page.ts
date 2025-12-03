import { Component } from '@angular/core';
import { CharacterService } from '../../../service/character-service';
import { RoleService } from '../../../service/role-service';

@Component({
  selector: 'app-create-character-page',
  imports: [],
  templateUrl: './create-character-page.html',
  styleUrl: './create-character-page.css',
})
export class CreateCharacterPage {
  roles: any[] = [];
  selectedRole: any | null = null;
  selectedRoleId: number | null = null;

  successMessage: string = '';

  character: any = {
    name: '',
    roleId: null,
    stats: null
  };

  constructor(
    private characterService: CharacterService,
    private roleService: RoleService
  ) { }

  ngOnInit(): void {
    this.loadRoles();
  }

  loadRoles(): void {
    this.roleService.getAll().subscribe({
      next: data => {
        this.roles = data;
      },
      error: err => console.error(err)
    });
  }

  onRoleChange(): void {
    this.selectedRole = this.roles.find(r => r.id == this.selectedRoleId);

    if (this.selectedRole) {
      // ========== STATS PAR DÉFAUT ==========
      this.character.roleId = this.selectedRole.id;
      this.character.stats = { ...this.selectedRole.stats };
      // (copie profonde pour éviter que l'utilisateur modifie les stats du rôle)
    }
  }

  createCharacter(): void {
    this.characterService.create(this.character).subscribe({
      next: created => {
        this.successMessage = `Personnage "${created.name}" créé avec succès !`;
      },
      error: err => console.error(err)
    });
  }
}