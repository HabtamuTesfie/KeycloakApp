import { Component, OnInit} from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder } from '@angular/forms';
import { PayService } from '../pg-confirmation/pay.service';
import { SharedService } from '../shared.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap'
import { DomSanitizer} from '@angular/platform-browser';

@Component({
  selector: 'jhi-hosted-payment',
  templateUrl: './hosted-payment.component.html',
  styleUrls: ['./hosted-payment.component.scss']
})
export class HostedPaymentComponent implements OnInit {
redirectUrl:any;
String1 : any = "https://payment.";
String2 : any;
String3 : any;
hostedPaymentUrl : any;
frame : any;
constructor(
        protected payService: PayService, protected fb: FormBuilder,
        private router : Router,
        private route : ActivatedRoute,
        private sharedService : SharedService,
        private modalService: NgbModal,
        public sanitizer: DomSanitizer
        )
        {

        }


    ngOnInit() : void{
             this.payService.getHostedPayment().subscribe((result:any)=>{
             this.redirectUrl = result;
             this.String2 = this.redirectUrl.body.partialRedirectUrl;
             this.String3 = this.String1.concat(this.String2.toString());

             this.paymentUrl(this.String3);

        });
    }

    paymentUrl(url:any):any{
          this.hostedPaymentUrl = this.sanitizer.bypassSecurityTrustResourceUrl(url);
        }

}
