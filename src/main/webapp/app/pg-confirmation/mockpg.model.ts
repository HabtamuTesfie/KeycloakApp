export interface IMockPg {
  name?: string | null;
  address?: string | null;
  creditCardNumber?: number;
  expireDate?: string | null;
}

export class MockPg implements IMockPg {
  constructor(
    public name?: string | null,
    public address?: string | null,
    public creditCardNumber?: number,
    public expireDate?: string | null,
  ) {}
}

export function getMockPgIdentifier(MockPg: IMockPg): number | undefined {
  return MockPg.creditCardNumber;
}
