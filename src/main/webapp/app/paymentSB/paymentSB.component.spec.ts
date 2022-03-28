import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PgModalComponent } from './pg-modal.component';

describe('PgModalComponent', () => {
  let component: PgModalComponent;
  let fixture: ComponentFixture<PgModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PgModalComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PgModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
