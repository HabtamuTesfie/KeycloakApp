import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPay } from '../pay.model';

@Component({
  selector: 'jhi-pay-detail',
  templateUrl: './pay-detail.component.html',
})
export class PayDetailComponent implements OnInit {
  pay: IPay | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ pay }) => {
      this.pay = pay;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
