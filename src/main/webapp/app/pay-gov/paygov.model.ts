export interface IPaygov {
  id?: number;
  cik?: string;
  ccc?: string | null;
  paymentAmount?: string | null;
  name?: string | null;
  email?: string | null;
  phone?: string | null;
}

export class Paygov implements IPaygov {
  constructor(
    public id?: number,
    public cik?: string,
    public ccc?: string | null,
    public paymentAmount?: string | null,
    public name?: string | null,
    public email?: string | null,
    public phone?: string | null
  ) {}
}

export function getPaygovIdentifier(paygov: IPaygov): number | undefined {
  return paygov.id;
}
