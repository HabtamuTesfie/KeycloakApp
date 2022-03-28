import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PayGovComponent } from './pay-gov.component';

describe('PayGovComponent', () => {
  let component: PayGovComponent;
  let fixture: ComponentFixture<PayGovComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PayGovComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PayGovComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
