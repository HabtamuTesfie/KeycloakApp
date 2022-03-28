import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { PayComponent } from '../list/pay.component';
import { PayDetailComponent } from '../detail/pay-detail.component';
import { PayUpdateComponent } from '../update/pay-update.component';
import { PayRoutingResolveService } from './pay-routing-resolve.service';

const payRoute: Routes = [
  {
    path: '',
    component: PayComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: PayDetailComponent,
    resolve: {
      pay: PayRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: PayUpdateComponent,
    resolve: {
      pay: PayRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: PayUpdateComponent,
    resolve: {
      pay: PayRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(payRoute)],
  exports: [RouterModule],
})
export class PayRoutingModule {}
