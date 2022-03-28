import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PgConfirmationComponent } from './pg-confirmation.component';

describe('PgConfirmationComponent', () => {
  let component: PgConfirmationComponent;
  let fixture: ComponentFixture<PgConfirmationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PgConfirmationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PgConfirmationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
