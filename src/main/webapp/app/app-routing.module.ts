import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { errorRoute } from './layouts/error/error.route';
import { navbarRoute } from './layouts/navbar/navbar.route';
import { DEBUG_INFO_ENABLED } from 'app/app.constants';
import { Authority } from 'app/config/authority.constants';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';

const LAYOUT_ROUTES = [navbarRoute, ...errorRoute];

@NgModule({
  imports: [
    RouterModule.forRoot(
      [
        {
          path: 'admin',
          data: {
            authorities: [Authority.ADMIN],
          },
          canActivate: [UserRouteAccessService],
          loadChildren: () => import('./admin/admin-routing.module').then(m => m.AdminRoutingModule),
        },
        {
          path: 'pay-gov',
          loadChildren: () => import('./pay-gov/pay-gov.module').then(m => m.PayGovModule),
        },
        {
          path: 'pg-confirmation',
          loadChildren: () => import('./pg-confirmation/pg-confirmation.module').then(m => m.PgConfirmationModule),
        },
        {
          path: 'payment-save',
          loadChildren: () => import('./payment-save/payment-save.module').then(m => m.PaymentSaveModule),
        },
        {
          path: 'payment-page',
          loadChildren: () => import('./hosted-payment/hosted-payment.module').then(m => m.HostedPaymentModule),
        },
        ...LAYOUT_ROUTES,
      ],
      { enableTracing: DEBUG_INFO_ENABLED }
    ),
  ],
  exports: [RouterModule],
})
export class AppRoutingModule {}
