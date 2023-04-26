import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SodaPurchaseComponent } from './soda-purchase.component';

describe('SodaPurchaseComponent', () => {
  let component: SodaPurchaseComponent;
  let fixture: ComponentFixture<SodaPurchaseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SodaPurchaseComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(SodaPurchaseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
