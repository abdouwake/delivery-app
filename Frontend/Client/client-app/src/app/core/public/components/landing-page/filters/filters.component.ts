import { Component, OnInit } from '@angular/core';
import { map, Observable } from 'rxjs';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { RestaurantService } from '../../../services/restaurant.service';
@Component({
  selector: 'app-filters',
  templateUrl: './filters.component.html',
  styleUrls: ['./filters.component.css'],
})
export class FiltersComponent implements OnInit {
  isSmallScreen$: Observable<boolean> | undefined;
  isLargeScreen$: Observable<boolean> | undefined;
  constructor(
    private breakpointObserver: BreakpointObserver,
    private restaurantService: RestaurantService
  ) {}

  restaurantName: string = '';
  restaurantPrice: string = 'null';
  restaurantPopularity: string = 'null';
  onlyFavorite: boolean = false;

  ngOnInit(): void {
    this.isSmallScreen$ = this.breakpointObserver
      .observe([Breakpoints.Small, Breakpoints.XSmall])
      .pipe(map(({ matches }) => matches));

    this.isLargeScreen$ = this.breakpointObserver
      .observe([Breakpoints.Web, Breakpoints.Medium])
      .pipe(map(({ matches }) => matches));
  }

  onSearchByName() {
    if (this.restaurantName == '') {
      this.restaurantService.getAll();
    }
    this.restaurantService.searchByName(this.restaurantName);
  }
}
