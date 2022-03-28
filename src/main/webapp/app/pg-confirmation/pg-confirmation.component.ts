/* eslint-disable no-console */
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder} from '@angular/forms';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';
import { IPay } from './pay.model';
import { PayService } from './pay.service';
import { SharedService } from '../shared.service';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';


@Component({
  selector: 'jhi-pg-confirmation',
  templateUrl: './pg-confirmation.component.html',
  styleUrls: ['./pg-confirmation.component.scss']
})
export class PgConfirmationComponent implements OnInit{

  isSaving = false;
  data:any;
  pgData:any;
  mockData:any;
  loading = true;
  timeLeft: any=10;
  interval:any;
  redirectUrl:any;

  String1 : any = "https://payment.";
  String2 : any;
  String3 : any;
  formValues: any;

      constructor(
        protected payService: PayService, protected fb: FormBuilder,
        private router : Router,
        private route : ActivatedRoute,
        private sharedService : SharedService,
        private modalService: NgbModal)
        {

        }

    ngOnInit() : void{
      this.formValues = sessionStorage.getItem('form');
      this.data = JSON.parse(this.formValues);

      this.payService.getPay(this.data).subscribe((msg:any)=>{
           console.log(msg)
      });

      this.startTimer();
      this.storedData(this.data);


    }

    startTimer() :any{
        this.interval = setInterval(() => {
          if(this.timeLeft > 0) {
            this.timeLeft--;
          } else {
               /* this.payService.getMockData().subscribe((result:any) => {
                 console.warn("result",result)
                 this.mockData = result;
                 });*/
                this.loading = false;
                document.getElementById("redirect")!.style.display = "none";
                this.pauseTimer();
                // this.hostedayment();
               /* this.payService.getHostedPayment().subscribe((result:any)=>{
                                     this.redirectUrl = result;
                                     this.String2 = this.redirectUrl.body.partialRedirectUrl;
                                     this.String3 = this.String1.concat(this.String2.toString());
                                   window.location.href = this.String3;*/

                                     this.payService.getSoapPayment().subscribe((result:any)=>{
                                      this.redirectUrl = JSON.stringify(result);
                                      this.String1=JSON.parse(this.redirectUrl);
                                      // this.String2 = this.redirectUrl.body.partialRedirectUrl;
                                       window.location.href = this.String1.body;


/*
                                       this.payService.getSoapPaymentGetCheckout().subscribe((result:any)=>{
                                        this.redirectUrl = JSON.stringify(result);
                                        this.String1=JSON.parse(this.redirectUrl);
                                        //this.String2 = this.redirectUrl.body.partialRedirectUrl;
                                         window.location.href = this.String1.body;*/


                         });
          }
        },1000)
    }

     pauseTimer():any {
       clearInterval(this.interval);
     }

     hostedpayment():void{
        this.router.navigate(['/payment-page']);
     }

    previousState(): void {
       window.history.back();
    }


    open() : any{
      this.modalService.open(PgModalComponent);
    }

    getFormData():void{
       this.data = this.sharedService.getData();
    }

    storedData(data:any) : void{
      const jsonData = JSON.stringify(data);
      sessionStorage.setItem("formData",jsonData);
    }




}


@Component({
  selector: 'jhi-pg-modal',
  template: `
    <div class="modal-header">
      <h4 class="modal-title"><strong>PayGov!</strong></h4>
      <button type="button" class="close" aria-label="Close" (click)="activeModal.dismiss('Cross click')">
        <span aria-hidden="true">&times;</span>
      </button>
    </div>
    <div class="modal-body">

    <div class="d-flex justify-content-center" *ngIf="loading">
      <div class="spinner-border" role="status" >
        <span class="sr-only" id="loading"></span>
      </div><br>
      <p>Please wait...</p>
    </div>

    <p id="redirect" align="center">Redirecting.... {{timeLeft}} Seconds</p>

    <div class="modal-footer">
      <button type="button" class="btn btn-outline-dark" (click)="activeModal.close('Close click')">Close</button>
      <button type="button" class="btn btn-outline-dark" (click)="save()" (click)="activeModal.close('Close click')">Pay</button>
    </div>
  `,
  styleUrls: ['./pg-confirmation.component.scss']
})


export class PgModalComponent {
    isSaving = false;
    data:any;
    pgData:any;
    mockData:any;
    loading = true;
    timeLeft: any=10;
    interval:any;

    constructor(
        protected payService: PayService,
        protected fb: FormBuilder,
        private router : Router,
        private route : ActivatedRoute,
        private sharedService : SharedService,
        private modalService: NgbModal,
        public activeModal: NgbActiveModal)
        {

        }

      /* ngOnInit() : void{
         /* this.getFormData();
          /*this.sharedService.getPost().subscribe((result:any) => {
                     console.warn("result",result)
                     this.pgData = result;
                  })
          this.startTimer();
      }*/



    startTimer() :any{
        this.interval = setInterval(() => {
          if(this.timeLeft > 0) {
            this.timeLeft--;
          } else {
            this.payService.getMockData().subscribe((result:any) => {
               console.warn("result",result)
                   this.mockData = result;

                   this.loading = false;
                   document.getElementById("redirect")!.style.display = "none";
                   this.pauseTimer();
            });
          }
        },1000)
      }

     pauseTimer():any {
       clearInterval(this.interval);
     }



      previousState(): void {
          window.history.back();
        }

    save(): void {
        this.isSaving = true;
        const pay = this.data;
        this.subscribeToSaveResponse(this.payService.create(pay));
    }

    open() : any{
          this.modalService.open(PgModalComponent);
      }


    openSuccess() : any{
          this.modalService.open(PgSuccessComponent);
    }

    getFormData():void{
       this.data = this.sharedService.getData();
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IPay>>): void {
      result.pipe(finalize(() => this.onSaveFinalize())).subscribe(
        () => this.onSaveSuccess(),
        () => this.onSaveError()
      );
    }

    protected onSaveSuccess(): void {
      this.openSuccess();
      this.previousState();

    }

    protected onSaveError(): void {
      // Api for inheritance.
    }

    protected onSaveFinalize(): void {
      this.isSaving = false;
    }



}



@Component({
  selector: 'jhi-pg-modal',
  template: `
    <div class="modal-header">
      <h4 class="modal-title"><strong>PayGov!</strong></h4>
      <button type="button" class="close" aria-label="Close" (click)="activeModal.dismiss('Cross click')">
        <span aria-hidden="true">&times;</span>
      </button>
    </div>

    <div class="modal-body">
      <p><strong> Payment submitted successfully ! </strong></p>
    </div>

    <div class="modal-footer">
      <button type="button" class="btn btn-outline-dark" (click)="activeModal.close('Close click')">Close</button>
    </div>
  `
})
export class PgSuccessComponent {

    isSaving = false;
    data:any;
    pgData:any;

    constructor(
        protected payService: PayService, protected fb: FormBuilder,
        private router : Router,
        private route : ActivatedRoute,
        private sharedService : SharedService,
        private modalService: NgbModal,
        public activeModal: NgbActiveModal)
        {

        }

}

/*
    <div>
       <ul>
           <p> Name : {{mockData.body.name}}</p>
           <p> Address : {{mockData.body.address}}</p>
           <p> Credit card number : {{mockData.body.creditCardNumber}}</p>
           <p> Expire date : {{mockData.body.expireDate}}</p>
       </ul>
    </div>
*/
