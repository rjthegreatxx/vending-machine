import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SodaDetailsComponent } from './soda-details.component';

describe('SodaDetailsComponent', () => {
  let component: SodaDetailsComponent;
  let fixture: ComponentFixture<SodaDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SodaDetailsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SodaDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});