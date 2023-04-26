import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddSodaComponent } from './add-soda.component';

describe('AddSodaComponent', () => {
  let component: AddSodaComponent;
  let fixture: ComponentFixture<AddSodaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddSodaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddSodaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
