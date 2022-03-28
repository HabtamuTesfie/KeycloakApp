import { Route } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { HostedPaymentComponent } from './hosted-payment.component';

export const HostedPaymentRoute: Route = {
  path: 'payment-page',
  component: HostedPaymentComponent,
  data: {
    pageTitle: ' PayGov Submit Payment ',
  },
  canActivate: [UserRouteAccessService],
};
