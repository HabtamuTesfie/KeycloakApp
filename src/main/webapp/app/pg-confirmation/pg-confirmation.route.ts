import { Route } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { PgConfirmationComponent } from './pg-confirmation.component';

export const PgConfirmation_Route: Route = {
  path: 'pg-confirmation',
  component: PgConfirmationComponent,
  data: {
    pageTitle: ' Pay-Gov Confirmation ',
  },
  canActivate: [UserRouteAccessService],
};
