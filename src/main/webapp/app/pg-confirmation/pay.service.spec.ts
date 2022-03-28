import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IPay, Pay } from '../pay.model';

import { PayService } from './pay.service';

describe('Pay Service', () => {
  let service: PayService;
  let httpMock: HttpTestingController;
  let elemDefault: IPay;
  let expectedResult: IPay | IPay[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(PayService);
    httpMock = TestBed.inject(HttpTestingController);

    elemDefault = {
      id: 0,
      cik: 0,
      ccc: 0,
      paymentAmount: 0,
      name: 'AAAAAAA',
      email: 'AAAAAAA',
      phone: 'AAAAAAA',
    };
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = Object.assign({}, elemDefault);

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(elemDefault);
    });

    it('should create a Pay', () => {
      const returnedFromService = Object.assign(
        {
          id: 0,
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.create(new Pay()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Pay', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          cik: 1,
          ccc: 1,
          paymentAmount: 1,
          name: 'BBBBBB',
          email: 'BBBBBB',
          phone: 'BBBBBB',
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.update(expected).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Pay', () => {
      const patchObject = Object.assign(
        {
          cik: 1,
          ccc: 1,
          name: 'BBBBBB',
          email: 'BBBBBB',
          phone: 'BBBBBB',
        },
        new Pay()
      );

      const returnedFromService = Object.assign(patchObject, elemDefault);

      const expected = Object.assign({}, returnedFromService);

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Pay', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          cik: 1,
          ccc: 1,
          paymentAmount: 1,
          name: 'BBBBBB',
          email: 'BBBBBB',
          phone: 'BBBBBB',
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toContainEqual(expected);
    });

    it('should delete a Pay', () => {
      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addPayToCollectionIfMissing', () => {
      it('should add a Pay to an empty array', () => {
        const pay: IPay = { id: 123 };
        expectedResult = service.addPayToCollectionIfMissing([], pay);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(pay);
      });

      it('should not add a Pay to an array that contains it', () => {
        const pay: IPay = { id: 123 };
        const payCollection: IPay[] = [
          {
            ...pay,
          },
          { id: 456 },
        ];
        expectedResult = service.addPayToCollectionIfMissing(payCollection, pay);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Pay to an array that doesn't contain it", () => {
        const pay: IPay = { id: 123 };
        const payCollection: IPay[] = [{ id: 456 }];
        expectedResult = service.addPayToCollectionIfMissing(payCollection, pay);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(pay);
      });

      it('should add only unique Pay to an array', () => {
        const payArray: IPay[] = [{ id: 123 }, { id: 456 }, { id: 14692 }];
        const payCollection: IPay[] = [{ id: 123 }];
        expectedResult = service.addPayToCollectionIfMissing(payCollection, ...payArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const pay: IPay = { id: 123 };
        const pay2: IPay = { id: 456 };
        expectedResult = service.addPayToCollectionIfMissing([], pay, pay2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(pay);
        expect(expectedResult).toContain(pay2);
      });

      it('should accept null and undefined values', () => {
        const pay: IPay = { id: 123 };
        expectedResult = service.addPayToCollectionIfMissing([], null, pay, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(pay);
      });

      it('should return initial array if no Pay is added', () => {
        const payCollection: IPay[] = [{ id: 123 }];
        expectedResult = service.addPayToCollectionIfMissing(payCollection, undefined, null);
        expect(expectedResult).toEqual(payCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
