export class Dish {
  id: number = -1;
  name: string = '';
  description: string = '';
  dishType: string = '';
  picture: string = '';
  unitPrice: number = 0;
  available: boolean = true;

  public constructor(fields?: {
    name?: string;
    address?: string;
    age?: number;
  }) {
    if (fields) Object.assign(this, fields);
  }
}
