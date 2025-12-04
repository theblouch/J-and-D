import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModifPerso } from './modif-perso';

describe('ModifPerso', () => {
  let component: ModifPerso;
  let fixture: ComponentFixture<ModifPerso>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ModifPerso]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModifPerso);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
