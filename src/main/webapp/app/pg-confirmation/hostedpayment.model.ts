export interface IHostedPayment {
  RETURNMAC?: string;
  hostedCheckoutId?: string;
  partialRedirectUrl?: string;
}

export class HostedPayment implements IHostedPayment {
  constructor(
    public RETURNMAC?: string,
    public hostedCheckoutId?: string,
    public partialRedirectUrl?: string,
  ) {}
}
