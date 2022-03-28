import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { SharedModule } from 'app/shared/shared.module';
import { PayGov_ROUTE } from './pay-gov.route';
import { PayGovComponent } from './pay-gov.component';

@NgModule({
   imports: [SharedModule, FormsModule, RouterModule.forChild([PayGov_ROUTE])],
  exports: [PayGovComponent],
  declarations: [PayGovComponent],
})
export class PayGovModule {}
