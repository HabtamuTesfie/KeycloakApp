import { Route } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { PaymentSBComponent } from './paymentSB.component';

export const PaymentSB_Route: Route = {
  path: 'paymentSB',
  component: PaymentSBComponent,
  data: {
    pageTitle: ' Payment Submit ',
  },
  canActivate: [UserRouteAccessService],
};
