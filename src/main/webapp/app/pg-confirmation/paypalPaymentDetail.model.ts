export interface Ipapal {
    paymentAmount?: string | null;
    paymentAction?: string | null;
    currencyCode?: string | null;
    userId?: number;
   
  }
  
  export class paypalPayment implements Ipapal {
    constructor(
      public paymentAmount?: string | null,
      public paymentAction?: string | null,
      public userId?: number,
      public currencyCode ?: string | null,
      
    ) {}
  }
  
  export function getpaypalPaymentIdentifier(paypalPg: Ipapal): number | undefined {
    return paypalPg.userId;
  }
  