import { Route } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { PaymentSaveComponent } from './payment-save.component';

export const PaymentSave_Route: Route = {
  path: 'payment-save',
  component: PaymentSaveComponent,
  data: {
    pageTitle: ' Payment Save ',
  },
  canActivate: [UserRouteAccessService],
};
