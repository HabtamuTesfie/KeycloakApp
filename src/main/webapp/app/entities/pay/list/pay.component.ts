import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IPay } from '../pay.model';
import { PayService } from '../service/pay.service';
import { PayDeleteDialogComponent } from '../delete/pay-delete-dialog.component';

@Component({
  selector: 'jhi-pay',
  templateUrl: './pay.component.html',
})
export class PayComponent implements OnInit {
  pays?: IPay[];
  isLoading = false;

  constructor(protected payService: PayService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.payService.query().subscribe(
      (res: HttpResponse<IPay[]>) => {
        this.isLoading = false;
        this.pays = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(index: number, item: IPay): number {
    return item.id!;
  }

  delete(pay: IPay): void {
    const modalRef = this.modalService.open(PayDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.pay = pay;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
