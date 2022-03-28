import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { SharedModule } from 'app/shared/shared.module';
import { PaymentSB_Route } from './paymentSB.route';
import { PaymentSBComponent } from './paymentSB.component';

@NgModule({
  imports: [SharedModule, FormsModule , RouterModule.forChild([PaymentSB_Route])],
  declarations: [PaymentSBComponent],
})
export class PaymentSBModule{}
