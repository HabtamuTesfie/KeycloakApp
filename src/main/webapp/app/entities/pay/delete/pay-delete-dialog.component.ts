import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IPay } from '../pay.model';
import { PayService } from '../service/pay.service';

@Component({
  templateUrl: './pay-delete-dialog.component.html',
})
export class PayDeleteDialogComponent {
  pay?: IPay;

  constructor(protected payService: PayService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.payService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
