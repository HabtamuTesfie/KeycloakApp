import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { IPay, Pay } from '../pay.model';
import { PayService } from '../service/pay.service';

@Component({
  selector: 'jhi-pay-update',
  templateUrl: './pay-update.component.html',
})
export class PayUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    cik: [],
    ccc: [],
    paymentAmount: [],
    name: [],
    email: [],
    phone: [],
    approval: [],
  });

  constructor(protected payService: PayService, protected activatedRoute: ActivatedRoute, protected fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ pay }) => {
      this.updateForm(pay);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const pay = this.createFromForm();
    if (pay.id !== undefined) {
      this.subscribeToSaveResponse(this.payService.update(pay));
    } else {
      this.subscribeToSaveResponse(this.payService.create(pay));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPay>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(pay: IPay): void {
    this.editForm.patchValue({
      id: pay.id,
      cik: pay.cik,
      ccc: pay.ccc,
      paymentAmount: pay.paymentAmount,
      name: pay.name,
      email: pay.email,
      phone: pay.phone,
      approval: pay.approval,
    });
  }

  protected createFromForm(): IPay {
    return {
      ...new Pay(),
      id: this.editForm.get(['id'])!.value,
      cik: this.editForm.get(['cik'])!.value,
      ccc: this.editForm.get(['ccc'])!.value,
      paymentAmount: this.editForm.get(['paymentAmount'])!.value,
      name: this.editForm.get(['name'])!.value,
      email: this.editForm.get(['email'])!.value,
      phone: this.editForm.get(['phone'])!.value,
      approval: this.editForm.get(['approval'])!.value,
    };
  }
}
