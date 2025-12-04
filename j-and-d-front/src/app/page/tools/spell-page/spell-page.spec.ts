import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SpellPage } from './spell-page';

describe('SpellPage', () => {
  let component: SpellPage;
  let fixture: ComponentFixture<SpellPage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SpellPage]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SpellPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
