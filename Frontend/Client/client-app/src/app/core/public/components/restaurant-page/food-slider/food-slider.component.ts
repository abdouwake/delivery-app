import { Component, OnInit } from '@angular/core';
import { map, Observable } from 'rxjs';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { RestaurantService } from '../../../services/restaurant.service';

@Component({
  selector: 'app-food-slider',
  templateUrl: './food-slider.component.html',
  styleUrls: ['./food-slider.component.css'],
})
export class FoodSliderComponent implements OnInit {
  constructor(
    private breakpointObserver: BreakpointObserver,
    private restoService: RestaurantService
  ) {}

  foodTypes: { key: string; title: string }[] = [{ key: 'ALL', title: 'ALL' }];
  selectedKey = 'ALL';
  isSmallScreen$: Observable<boolean> | undefined;
  isLargeScreen$: Observable<boolean> | undefined;
  items: string[] = [];

  ngOnInit(): void {
    this.restoService.foodTypes.subscribe((type) => {
      this.foodTypes = type;
    });
    this.isSmallScreen$ = this.breakpointObserver
      .observe([Breakpoints.Small, Breakpoints.XSmall])
      .pipe(map(({ matches }) => matches));

    this.isLargeScreen$ = this.breakpointObserver
      .observe([Breakpoints.Web, Breakpoints.Medium])
      .pipe(map(({ matches }) => matches));

    this.restoService.restaurant.value.Dish.map((dish) => {
      if (!this.isPresentInFoodType(dish.dishType)) {
        this.foodTypes.push({ key: dish.dishType, title: dish.name });
      }
    });
  }

  isPresentInFoodType(title: string) {
    var exists: boolean = false;
    this.foodTypes.map((dish) => {
      if (dish.key == title) {
        exists = true;
      }
    });
    return exists;
  }

  onSelect(selected: string) {
    this.selectedKey = selected;
    if (selected == 'ALL') {
      this.restoService.getAll();
    } else {
      this.restoService.getAllByType(selected);
    }
  }
}
