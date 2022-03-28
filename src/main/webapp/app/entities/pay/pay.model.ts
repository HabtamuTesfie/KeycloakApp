export interface IPay {
  id?: number;
  cik?: string | null;
  ccc?: string | null;
  paymentAmount?: number | null;
  name?: string | null;
  email?: string | null;
  phone?: string | null;
  approval?: string | null;
}

export class Pay implements IPay {
  constructor(
    public id?: number,
    public cik?: string | null,
    public ccc?: string | null,
    public paymentAmount?: number | null,
    public name?: string | null,
    public email?: string | null,
    public phone?: string | null,
    public approval?: string | null
  ) {}
}

export function getPayIdentifier(pay: IPay): number | undefined {
  return pay.id;
}
