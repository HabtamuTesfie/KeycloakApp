import { Route } from '@angular/router';
import { PayGovComponent } from './pay-gov.component';

export const PayGov_ROUTE: Route = {
  path: 'pay-gov',
  component: PayGovComponent,
  data: {
    pageTitle: 'Welcome, PayGov !',
  },
};

