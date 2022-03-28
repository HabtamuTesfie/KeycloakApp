export interface IPay {
  id?: number;
  cik?: number | null;
  ccc?: number | null;
  paymentAmount?: number | null;
  name?: string | null;
  email?: string | null;
  phone?: string | null;
}

export class Pay implements IPay {
  constructor(
    public id?: number,
    public cik?: number | null,
    public ccc?: number | null,
    public paymentAmount?: number | null,
    public name?: string | null,
    public email?: string | null,
    public phone?: string | null
  ) {}
}

export function getPayIdentifier(pay: IPay): number | undefined {
  return pay.id;
}
