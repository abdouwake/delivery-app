import { Dish } from './DishDto';

export class Restaurant {
  id: number = -1;
  idOwner: number = -1;
  name: string = '';
  description: string = '';
  adress: string = '';
  email: string = '';
  picture: string = '';
  number: number = -1;
  suspended: boolean = false;
  creationDate: string = '';
  lastUpdate: string = '';
  foodType: string = '';
  Dish: Array<Dish> = [];

  public constructor(fields?: {
    name?: string;
    address?: string;
    age?: number;
  }) {
    if (fields) Object.assign(this, fields);
  }
}
