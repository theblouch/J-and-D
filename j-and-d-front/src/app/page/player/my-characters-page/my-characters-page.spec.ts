import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MyCharactersPage } from './my-characters-page';

describe('MyCharactersPage', () => {
  let component: MyCharactersPage;
  let fixture: ComponentFixture<MyCharactersPage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MyCharactersPage]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MyCharactersPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
