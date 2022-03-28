import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IPay, Pay } from '../pay.model';
import { PayService } from '../service/pay.service';

@Injectable({ providedIn: 'root' })
export class PayRoutingResolveService implements Resolve<IPay> {
  constructor(protected service: PayService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPay> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((pay: HttpResponse<Pay>) => {
          if (pay.body) {
            return of(pay.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Pay());
  }
}
