import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NpcPage } from './npc-page';

describe('NpcPage', () => {
  let component: NpcPage;
  let fixture: ComponentFixture<NpcPage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NpcPage]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NpcPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
