import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { FormBuilder } from '@angular/forms';
import { HostListener } from '@angular/core';
import { SharedService } from '../shared.service';
import { PayService } from '../pg-confirmation/pay.service';

import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
SharedService;
@Component({
  selector: 'jhi-pay-gov',
  templateUrl: './pay-gov.component.html',
  styleUrls: ['./pay-gov.component.scss'],
})
export class PayGovComponent implements OnInit {
  form : any;
  canClose : any;
  tabWasClosed : any;


  constructor(
    private router : Router,
    private route : ActivatedRoute,
    private sharedService : SharedService,
    private modalService: NgbModal,
    protected fb: FormBuilder
    )
    {
        this.form = new FormGroup({
          cik: new FormControl("",Validators.compose([Validators.required,Validators.pattern('^[0-9]+[@#$%^&*()!]')])),
          paymentAmount: new FormControl("",Validators.required),
          ccc: new FormControl("",Validators.compose([Validators.required,Validators.min(100000)])),
          name: new FormControl("",Validators.required),
          email: new FormControl("",Validators.required),
          phone: new FormControl("",Validators.compose([Validators.required,Validators.pattern('^[0-9]{10}$')]))

        })


    }


    ngOnInit() : void {
     const formValues = sessionStorage.getItem('form');
        if (formValues) {
          this.applyFormValues(this.form, JSON.parse(formValues));
        }

       this.storeData();

       this.
       form.
       valueChanges.
       subscribe((form:any) => {
         sessionStorage.setItem('form', JSON.stringify(form));
       });

    }


    previousState(): void {
        window.history.back();
    }

    storeData() : void{
        const data : any = this.form.value;
        this.sharedService.setData(data);
    }

    // eslint-disable-next-line @typescript-eslint/explicit-function-return-type
    applyFormValues (group:any, formValues:any) {
    Object.keys(formValues).forEach((key:any) => {
      const formControl = <FormControl>group.controls[key];

       if (formControl instanceof FormGroup) {
         this.applyFormValues(formControl, formValues[key]);
       } else {
        formControl.setValue(formValues[key]);
       }

    });
    }
  @HostListener(' window:beforeunload', ['$event'])
  showAlertMessageWhenClosingTab($event: any): void {
    event?.preventDefault();
    $event.returnValue(this.openSuccess());
  }

  openSuccess(): any {
   this.modalService.open(PgSuccessComponent);
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
      <p><strong>Are you sure to close the window? </strong></p>
    </div>

    <div class="modal-footer">
      <button type="button" class="btn btn-outline-dark" (click)="close()">Close</button>
    </div>
  `,
})
export class PgSuccessComponent {
  isSaving = false;
  data: any;
  pgData: any;

  constructor(
    protected payService: PayService,
    protected fb: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
    private sharedService: SharedService,
    private modalService: NgbModal,
    public activeModal: NgbActiveModal
  ) {}

  close(): void {
    window.close();
    window.setTimeout(function () {
      location.href = '/index.html';
    }, 100000);
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
