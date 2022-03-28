import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { SharedModule } from 'app/shared/shared.module';
import { PgConfirmation_Route } from './pg-confirmation.route';
import { PgConfirmationComponent } from './pg-confirmation.component';
import { PgModalComponent } from './pg-confirmation.component';



@NgModule({
  imports: [SharedModule, FormsModule , RouterModule.forChild([PgConfirmation_Route])],
  declarations: [PgConfirmationComponent,PgModalComponent],
})
export class PgConfirmationModule {}
