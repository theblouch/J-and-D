import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GestionSession } from './gestion-session';

describe('GestionSession', () => {
  let component: GestionSession;
  let fixture: ComponentFixture<GestionSession>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GestionSession]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GestionSession);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
