import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Observable, of } from 'rxjs';
import { ItemDto } from '../../../dto/item-dto';
import { ItemService } from '../../../service/item-service';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-item',
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './item-page.html',
  styleUrl: './item-page.css',
})
export class ItemPage implements OnInit {
  items$!: Observable<ItemDto[]>;
  itemForm!: FormGroup;
  editingItem: ItemDto | null = null;
  showForm: boolean = false;

  constructor(private formBuilder: FormBuilder, private itemService: ItemService, private router: Router

  ) { }

  ngOnInit(): void {
    // Charger les items existants
    this.items$ = this.itemService.findAll();

    // Initialiser le formulaire
    this.itemForm = this.formBuilder.group({
      name: ['', Validators.required],
      description: ['', Validators.required],
      basedOnStrength: [false],
      baseDamage: [[]],
      armorValue: [0, Validators.required],
    });
  }

  public creer(): void {
    const formValue = this.itemForm.value;

    const newItem = new ItemDto(
      this.editingItem ? this.editingItem.id : 0,
      formValue.name,
      formValue.description,
      formValue.basedOnStrength,
      Array.isArray(formValue.baseDamage) ? formValue.baseDamage : [],
      formValue.armorValue
    );

    this.itemService.save(newItem);

    this.showForm = false;
    this.editingItem = null;
    this.itemForm.reset();
  }

  public editer(item: ItemDto): void {
    this.editingItem = item;
    this.showForm = true;

    this.itemForm.setValue({
      name: item.name,
      description: item.description,
      basedOnStrength: item.basedOnStrength,
      baseDamage: item.baseDamage,
      armorValue: item.armorValue,
    });
  }

  goHome() {
    this.router.navigate(['/tools']);
  }

  public annulerEditer(): void {
    this.showForm = false;
    this.editingItem = null;
    this.itemForm.reset();
  }

  public delete(id: number): void {
    this.itemService.deleteById(id);
  }

  public displayDamage(damage: number[]): string {
    return damage.length > 0 ? damage.join(', ') : 'vide :(';
  }

  public updateBaseDamage(event: Event): void {
    const input = event.target as HTMLInputElement;
    const values = input.value.split(',').map((x) => Number(x.trim()));
    this.itemForm.patchValue({ baseDamage: values });
  }
  public updateArmorValue(event: Event): void {
    const input = event.target as HTMLInputElement;
    const value = Number(input.value.trim());
    this.itemForm.patchValue({ armorValue: value });
  }
}
