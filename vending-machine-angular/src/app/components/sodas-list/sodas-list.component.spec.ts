import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SodasListComponent } from './sodas-list.component';

describe('SodasListComponent', () => {
  let component: SodasListComponent;
  let fixture: ComponentFixture<SodasListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SodasListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SodasListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});