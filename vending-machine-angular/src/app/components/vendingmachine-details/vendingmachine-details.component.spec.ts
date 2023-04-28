import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VendingmachineDetailsComponent } from './vendingmachine-details.component';

describe('VendingmachineDetailsComponent', () => {
  let component: VendingmachineDetailsComponent;
  let fixture: ComponentFixture<VendingmachineDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [VendingmachineDetailsComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(VendingmachineDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
