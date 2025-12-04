import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Observable, of } from 'rxjs';
import { ItemDto } from '../../dto/item-dto';
import { ItemService } from '../../service/item-service';

@Component({
  selector: 'app-item',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './item-page.html',
  styleUrl: './item-page.css',
})
export class ItemPage implements OnInit {
  items$!: Observable<ItemDto[]>;
  itemForm!: FormGroup;
  editingItem: ItemDto | null = null;
  showForm: boolean = false;

  constructor(private formBuilder: FormBuilder, private itemService: ItemService) {}

  ngOnInit(): void {
    // Charger les items existants
    this.items$ = this.itemService.findAll();

    // Initialiser le formulaire
    this.itemForm = this.formBuilder.group({
      name: ['', Validators.required],
      description: ['', Validators.required],
      basedOnStrength: [false],
      baseDamage: [[]], // tableau de nombres
    });
  }

  public creer(): void {
    const formValue = this.itemForm.value;

    const newItem = new ItemDto(
      0,
      formValue.name,
      formValue.description,
      formValue.basedOnStrength,
      Array.isArray(formValue.baseDamage) ? formValue.baseDamage : []
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
    });
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
}
