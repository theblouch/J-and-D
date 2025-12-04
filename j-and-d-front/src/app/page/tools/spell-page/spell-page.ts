import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { SpellDto } from '../../../dto/spell-dto';
import { SpellService } from '../../../service/spell-service';
import { RoleService } from '../../../service/role-service';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-spell-page',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './spell-page.html',
  styleUrls: ['./spell-page.css'],
})
export class SpellPage implements OnInit {
  spells$!: Observable<SpellDto[]>;
  roles$!: Observable<any[]>;
  roles: any[] = []; // ✅ tableau local pour find()
  spellForm!: FormGroup;
  editingSpell: SpellDto | null = null;
  showForm: boolean = false;

  constructor(
    private formBuilder: FormBuilder,
    private spellService: SpellService,
    private roleService: RoleService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.spells$ = this.spellService.findAll();
    this.roles$ = this.roleService.getAll();
    this.roles$.subscribe((r) => (this.roles = r));
    this.roleService.getAll().subscribe((r) => {
      console.log('Réponse backend /role:', r);
      this.roles = r;
    });

    this.spellForm = this.formBuilder.group({
      name: ['', Validators.required],
      description: ['', Validators.required],
      spellLevel: [1, Validators.required],
      role: [null, Validators.required],
      baseDamage: [[]],
    });
  }

  goHome() {
    this.router.navigate(['/tools']);
  }

  public creer(): void {
    const formValue = this.spellForm.value;

    const newSpell = new SpellDto(
      this.editingSpell ? this.editingSpell.id : 0,
      formValue.name,
      formValue.description,
      formValue.spellLevel,
      formValue.role,
      Array.isArray(formValue.baseDamage) ? formValue.baseDamage : []
    );

    this.spellService.save(newSpell);
    this.showForm = false;
    this.editingSpell = null;
    this.spellForm.reset();
  }

  public editer(spell: SpellDto): void {
    this.editingSpell = spell;
    this.showForm = true;

    this.spellForm.setValue({
      name: spell.name,
      description: spell.description,
      spellLevel: spell.spellLevel,
      role: spell.role,
      baseDamage: spell.baseDamage,
    });
  }

  public annulerEditer(): void {
    this.showForm = false;
    this.editingSpell = null;
    this.spellForm.reset();
  }

  public delete(id: number): void {
    this.spellService.deleteById(id);
  }

  public updateBaseDamage(event: Event): void {
    const input = event.target as HTMLInputElement;
    const values = input.value.split(',').map((x) => Number(x.trim()));
    this.spellForm.patchValue({ baseDamage: values });
  }

  public displayDamage(damage: number[]): string {
    return damage.length > 0 ? damage.join(', ') : 'vide :(';
  }

  public getRoleName(roleId: number): string {
    const found = this.roles.find((r) => r.id === roleId);
    return found ? found.name : 'Inconnu';
  }
}
