import { Component } from '@angular/core';
import { CharacterService } from '../../../service/character-service';
import { CommonModule } from '@angular/common';
import { RoleService } from '../../../service/role-service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-my-characters-page',
  imports: [CommonModule],
  templateUrl: './my-characters-page.html',
  styleUrl: './my-characters-page.css',
})
export class MyCharactersPage {
  characters: any[] = [];
  roles: any[] = [];
  errorMessage: string | null = null;

  constructor(private characterService: CharacterService, private roleService: RoleService, private router: Router) { }

  ngOnInit(): void {
    this.loadCharacters();
    this.loadRoles();
  }

  loadCharacters(): void {
    this.characterService.getAll().subscribe({
      next: data => {
        this.characters = data;
      },
      error: err => {
        console.error(err);
        this.errorMessage = 'Erreur lors du chargement des personnages.';
      }
    });
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
    const role = this.roles.find(r => r.id === roleId);
    return role ? role.name : 'Inconnu';
  }

  goToCharacter(id: number): void {
    this.router.navigate(['/mycharacters', id]);
  }
}

