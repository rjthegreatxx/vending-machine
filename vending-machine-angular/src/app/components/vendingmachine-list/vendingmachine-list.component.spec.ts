import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VendingmachineListComponent } from './vendingmachine-list.component';

describe('VendingmachineListComponent', () => {
  let component: VendingmachineListComponent;
  let fixture: ComponentFixture<VendingmachineListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [VendingmachineListComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(VendingmachineListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
