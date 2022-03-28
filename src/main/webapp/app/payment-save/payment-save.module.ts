import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { SharedModule } from 'app/shared/shared.module';
import { PaymentSave_Route } from './payment-save.route';
import { PaymentSaveComponent } from './payment-save.component';

@NgModule({
  imports: [SharedModule, FormsModule , RouterModule.forChild([PaymentSave_Route])],
  declarations: [PaymentSaveComponent],
})
export class PaymentSaveModule {}
