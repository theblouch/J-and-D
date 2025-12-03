import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateSession } from './create-session';

describe('CreateSession', () => {
  let component: CreateSession;
  let fixture: ComponentFixture<CreateSession>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CreateSession]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreateSession);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
