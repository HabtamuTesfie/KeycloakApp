import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { SharedModule } from 'app/shared/shared.module';
import { HostedPaymentRoute } from './hosted-payment.route';
import { HostedPaymentComponent } from './hosted-payment.component';

@NgModule({
  imports: [SharedModule, FormsModule , RouterModule.forChild([HostedPaymentRoute])],
  declarations: [HostedPaymentComponent],
})
export class HostedPaymentModule{}
