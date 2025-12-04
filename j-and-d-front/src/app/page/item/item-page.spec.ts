import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ItemPage } from './item-page';

describe('Item', () => {
  let component: ItemPage;
  let fixture: ComponentFixture<ItemPage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ItemPage],
    }).compileComponents();

    fixture = TestBed.createComponent(ItemPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
