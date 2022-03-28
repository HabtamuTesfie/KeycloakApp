import { Component, OnInit } from '@angular/core';
import { NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';
import { NgbModal} from '@ng-bootstrap/ng-bootstrap';
import { ActivatedRoute,Router } from '@angular/router';
import { SharedService } from '../shared.service';
import { PayService } from '../pg-confirmation/pay.service';
import { IPay } from '../pg-confirmation/pay.model';
import { Observable } from 'rxjs';
import { HttpResponse } from '@angular/common/http';
import { finalize } from 'rxjs/operators';


@Component({
  selector: 'jhi-pg-modal',
  templateUrl: './payment-save.component.html',
  styleUrls: ['./payment-save.component.scss']
})

export class PaymentSaveComponent implements OnInit {
isSaving = false;
lsData:any;
formData:any;
data:any;
  mockData: any;

        constructor(protected activatedRoute: ActivatedRoute,
               private router: Router,
               private route: ActivatedRoute,
               private modalService: NgbModal,
               protected payService: PayService,
               private sharedService : SharedService

         ) {}

         ngOnInit() : void{
            this.lsData = sessionStorage.getItem('formData');
            this.data = JSON.parse(this.lsData);


           this.payService.getSoapPaymentGetCheckout().subscribe((result:any) => {
                console.warn("result",result)
                this.mockData = result;
            });


         }






         save(): void {
           this.isSaving = true;
           const pay = this.data;
           this.subscribeToSaveResponse(this.payService.create(pay));
            }


            openSuccess() : any{
              this.modalService.open(SuccessComponent);
              }

           openError() : any{
             this.modalService.open(ErrorComponent);
           }

           destroyFormValues() : any{
               sessionStorage.removeItem('form');
           }


           previousState(): void {
              window.history.back();
          }


           protected subscribeToSaveResponse(result: Observable<HttpResponse<IPay>>): void {
             result.pipe(finalize(() => this.onSaveFinalize())).subscribe(
               () => this.onSaveSuccess(),
               () => this.onSaveError()
             );
           }

           protected onSaveSuccess(): void {
            this.destroyFormValues();
             this.router.navigate(['/']);
             this.openSuccess();
           }

           protected onSaveError(): void {
             this.router.navigate(['/payment-page']);
             this.openError();
           }

           protected onSaveFinalize(): void {
             this.isSaving = false;
           }


}


@Component({
  selector: 'jhi-my-modal',
  template: `
    <div class="modal-header">
      <h4 class="modal-title"><strong>Confirmation Message</strong></h4>
      <button type="button" class="close" aria-label="Close" (click)="activeModal.dismiss('Cross click')">
        <span aria-hidden="true">&times;</span>
      </button>
    </div>

    <div class="modal-body">
      <br>
      <h6> Payment process completed successfully ! </h6>
      <br>
    </div>

    <div class="modal-footer">
      <button type="button" class="btn btn-outline-dark"  (click)="activeModal.dismiss('Cross click')" (click)="finish()">OK</button>
    </div>
  `
})
export class SuccessComponent {

    data:any;

    constructor(
        private router : Router,
        private route : ActivatedRoute,
        private modalService: NgbModal,
        public activeModal: NgbActiveModal)
        {

        }

        finish():void{
        parent.window.close();
        this.router.navigate(['/']);
        }

}

@Component({
  selector: 'jhi-my-modal',
  template: `
    <div class="modal-header">
      <h4 class="modal-title"><strong>Confirmation Message</strong></h4>
      <button type="button" class="close" aria-label="Close" (click)="activeModal.dismiss('Cross click')">
        <span aria-hidden="true">&times;</span>
      </button>
    </div>

    <div class="modal-body">
      <br>
      <h6> Unable to finish payment process, please try again ! </h6>
      <br>
    </div>

    <div class="modal-footer">
      <button type="button" class="btn btn-outline-dark"  (click)="activeModal.dismiss('Cross click')" (click)="finish()">OK</button>
    </div>
  `
})
export class ErrorComponent {

    data:any;

    constructor(
        private router : Router,
        private route : ActivatedRoute,
        private modalService: NgbModal,
        public activeModal: NgbActiveModal)
        {

        }

        finish():void{
           this.router.navigate(['/']);
        }


}
