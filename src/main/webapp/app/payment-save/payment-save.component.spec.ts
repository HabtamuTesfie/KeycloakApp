import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PaymentSaveComponent } from './payment-save.component';

describe('PaymentSaveComponent', () => {
  let component: PaymentSaveComponent;
  let fixture: ComponentFixture<PaymentSaveComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PaymentSaveComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PaymentSaveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
