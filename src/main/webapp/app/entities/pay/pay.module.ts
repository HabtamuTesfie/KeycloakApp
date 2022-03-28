import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { PayComponent } from './list/pay.component';
import { PayDetailComponent } from './detail/pay-detail.component';
import { PayUpdateComponent } from './update/pay-update.component';
import { PayDeleteDialogComponent } from './delete/pay-delete-dialog.component';
import { PayRoutingModule } from './route/pay-routing.module';

@NgModule({
  imports: [SharedModule, PayRoutingModule],
  declarations: [PayComponent, PayDetailComponent, PayUpdateComponent, PayDeleteDialogComponent],
  entryComponents: [PayDeleteDialogComponent],
})
export class PayModule {}
