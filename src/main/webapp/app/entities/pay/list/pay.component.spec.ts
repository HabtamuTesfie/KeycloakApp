import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { PayService } from '../service/pay.service';

import { PayComponent } from './pay.component';

describe('Pay Management Component', () => {
  let comp: PayComponent;
  let fixture: ComponentFixture<PayComponent>;
  let service: PayService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [PayComponent],
    })
      .overrideTemplate(PayComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(PayComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(PayService);

    const headers = new HttpHeaders();
    jest.spyOn(service, 'query').mockReturnValue(
      of(
        new HttpResponse({
          body: [{ id: 123 }],
          headers,
        })
      )
    );
  });

  it('Should call load all on init', () => {
    // WHEN
    comp.ngOnInit();

    // THEN
    expect(service.query).toHaveBeenCalled();
    expect(comp.pays?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });
});
