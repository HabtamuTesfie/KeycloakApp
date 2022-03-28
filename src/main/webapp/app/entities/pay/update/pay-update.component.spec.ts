jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { PayService } from '../service/pay.service';
import { IPay, Pay } from '../pay.model';

import { PayUpdateComponent } from './pay-update.component';

describe('Pay Management Update Component', () => {
  let comp: PayUpdateComponent;
  let fixture: ComponentFixture<PayUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let payService: PayService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [PayUpdateComponent],
      providers: [FormBuilder, ActivatedRoute],
    })
      .overrideTemplate(PayUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(PayUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    payService = TestBed.inject(PayService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const pay: IPay = { id: 456 };

      activatedRoute.data = of({ pay });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(pay));
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<Pay>>();
      const pay = { id: 123 };
      jest.spyOn(payService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ pay });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: pay }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(payService.update).toHaveBeenCalledWith(pay);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<Pay>>();
      const pay = new Pay();
      jest.spyOn(payService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ pay });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: pay }));
      saveSubject.complete();

      // THEN
      expect(payService.create).toHaveBeenCalledWith(pay);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<Pay>>();
      const pay = { id: 123 };
      jest.spyOn(payService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ pay });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(payService.update).toHaveBeenCalledWith(pay);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
