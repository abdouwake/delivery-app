import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ApiHttpServiceService } from './api-calls/api-http-service.service';
import { ApiPaths } from './api-calls/ApiPaths';
import { JWTTokenService } from './Utilities/jwttoken.service';
import { BehaviorSubject } from 'rxjs';
import { Restaurant } from '../models/Restaurant.dto';

@Injectable({
  providedIn: 'root',
})
export class RestaurantService {
  constructor(
    private http: HttpClient,
    private jWTTokenService: JWTTokenService,
    private clientApi: ApiHttpServiceService
  ) {}

  restaurantList: BehaviorSubject<Restaurant[]> = new BehaviorSubject<
    Restaurant[]
  >([]);

  restaurant: BehaviorSubject<Restaurant> = new BehaviorSubject<Restaurant>(
    new Restaurant()
  );
  foodTypes: BehaviorSubject<{ key: string; title: string }[]> =
    new BehaviorSubject<{ key: string; title: string }[]>([
      { key: 'ALL', title: 'ALL' },
    ]);

  getAll() {
    this.clientApi.get(ApiPaths.AllRestaurant).subscribe((res: any) => {
      console.log(res);
      if (res.data != null) {
        this.restaurantList.next(res.data);
      }
    });
  }

  getAllTypesOfFood() {
    this.clientApi.get(ApiPaths.GetAllTypesOfFood).subscribe((res: any) => {
      if (res.data != null) {
        this.restaurantList.next(res.data);
      }
    });
  }

  getRestaurantById(id: number) {
    this.clientApi
      .get(ApiPaths.getRestaurantById + id)
      .subscribe((res: any) => {
        if (res.data != null) {
          this.restaurant.next(res.data);
        }
      });
  }

  searchByName(name: string) {
    this.clientApi
      .get(ApiPaths.SearchRestaurantByName + name)
      .subscribe((res: any) => {
        if (res.data != null) {
          this.restaurantList.next(res.data);
        }
      });
  }

  getAllByType(type: string) {
    this.clientApi
      .get(ApiPaths.AllRestaurantByType + type)
      .subscribe((res: any) => {
        if (res.data != null) {
          this.restaurantList.next(res.data);
        }
      });
  }
}
