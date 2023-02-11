import { Component, OnInit } from '@angular/core';
import { Observable, map } from 'rxjs';
import { Restaurant } from '../../../models/Restaurant.dto';
import { RestaurantService } from '../../../services/restaurant.service';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-restaurant-card',
  templateUrl: './restaurant-card.component.html',
  styleUrls: ['./restaurant-card.component.css'],
})
export class RestaurantCardComponent implements OnInit {
  constructor(
    private restaurantService: RestaurantService,
    private breakpointObserver: BreakpointObserver,
    private route: ActivatedRoute,
    private router: Router
  ) {}
  restaurant: Restaurant[] = [];

  isSmallScreen$: Observable<boolean> | undefined;
  isLargeScreen$: Observable<boolean> | undefined;

  ngOnInit(): void {
    this.isSmallScreen$ = this.breakpointObserver
      .observe([Breakpoints.Small, Breakpoints.XSmall])
      .pipe(map(({ matches }) => matches));

    this.isLargeScreen$ = this.breakpointObserver
      .observe([Breakpoints.Web, Breakpoints.Medium])
      .pipe(map(({ matches }) => matches));

    this.restaurantService.getAll();
    this.restaurantService.restaurantList.subscribe(
      (restaurant) => (this.restaurant = restaurant)
    );
  }

  OpenRestaurantDetails(id: number) {
    let url: string = '/restaurant/' + id;
    this.router.navigateByUrl(url);
  }
}
