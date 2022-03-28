import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { PayDetailComponent } from './pay-detail.component';

describe('Pay Management Detail Component', () => {
  let comp: PayDetailComponent;
  let fixture: ComponentFixture<PayDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PayDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ pay: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(PayDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(PayDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load pay on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.pay).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
