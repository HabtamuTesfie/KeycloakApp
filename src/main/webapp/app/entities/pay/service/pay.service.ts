import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IPay, getPayIdentifier } from '../pay.model';

export type EntityResponseType = HttpResponse<IPay>;
export type EntityArrayResponseType = HttpResponse<IPay[]>;

@Injectable({ providedIn: 'root' })
export class PayService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/pays');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(pay: IPay): Observable<EntityResponseType> {
    return this.http.post<IPay>(this.resourceUrl, pay, { observe: 'response' });
  }

  update(pay: IPay): Observable<EntityResponseType> {
    return this.http.put<IPay>(`${this.resourceUrl}/${getPayIdentifier(pay) as number}`, pay, { observe: 'response' });
  }

  partialUpdate(pay: IPay): Observable<EntityResponseType> {
    return this.http.patch<IPay>(`${this.resourceUrl}/${getPayIdentifier(pay) as number}`, pay, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IPay>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPay[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addPayToCollectionIfMissing(payCollection: IPay[], ...paysToCheck: (IPay | null | undefined)[]): IPay[] {
    const pays: IPay[] = paysToCheck.filter(isPresent);
    if (pays.length > 0) {
      const payCollectionIdentifiers = payCollection.map(payItem => getPayIdentifier(payItem)!);
      const paysToAdd = pays.filter(payItem => {
        const payIdentifier = getPayIdentifier(payItem);
        if (payIdentifier == null || payCollectionIdentifiers.includes(payIdentifier)) {
          return false;
        }
        payCollectionIdentifiers.push(payIdentifier);
        return true;
      });
      return [...paysToAdd, ...payCollection];
    }
    return payCollection;
  }
}
