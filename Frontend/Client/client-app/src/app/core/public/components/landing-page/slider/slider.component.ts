import { Component, OnInit } from '@angular/core';
import { map, Observable } from 'rxjs';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { RestaurantService } from '../../../services/restaurant.service';

@Component({
  selector: 'app-slider',
  templateUrl: './slider.component.html',
  styleUrls: ['./slider.component.css'],
})
export class SliderComponent implements OnInit {
  constructor(
    private breakpointObserver: BreakpointObserver,
    private restoService: RestaurantService
  ) {}

  types = [
    { key: 'ALL', title: 'All', icon: 'fastfood' },
    { key: 'PIZZA', title: 'Pizza', icon: 'local_pizza' },
    { key: 'SNACK', title: 'Snack', icon: 'category' },
    { key: 'BURGER', title: 'Burger', icon: 'fastfood' },
    { key: 'DISH', title: 'Dish', icon: 'restaurant_menu' },
    { key: 'TACOS', title: 'Tacos', icon: 'table_chart' },
    { key: 'DESERT', title: 'Desert', icon: 'cake' },
    { key: 'SALAD', title: 'Salad', icon: 'brightness_7' },
    { key: 'DRINKS', title: 'Drinks', icon: 'free_breakfast' },
    { key: 'SOON', title: 'Soon', icon: 'kitchen' },
    { key: 'SOON', title: 'Soon', icon: 'kitchen' },
    { key: 'SOON', title: 'Soon', icon: 'kitchen' },
    { key: 'SOON', title: 'Soon', icon: 'kitchen' },
    { key: 'SOON', title: 'Soon', icon: 'kitchen' },
    { key: 'SOON', title: 'Soon', icon: 'kitchen' },
    { key: 'SOON', title: 'Soon', icon: 'kitchen' },
    { key: 'SOON', title: 'Soon', icon: 'kitchen' },
    { key: 'SOON', title: 'Soon', icon: 'kitchen' },
    { key: 'SOON', title: 'Soon', icon: 'kitchen' },
    { key: 'SOON', title: 'Soon', icon: 'kitchen' },
    { key: 'SOON', title: 'Soon', icon: 'kitchen' },
  ];
  selectedKey = 'ALL';
  isSmallScreen$: Observable<boolean> | undefined;
  isLargeScreen$: Observable<boolean> | undefined;
  ngOnInit(): void {
    this.isSmallScreen$ = this.breakpointObserver
      .observe([Breakpoints.Small, Breakpoints.XSmall])
      .pipe(map(({ matches }) => matches));

    this.isLargeScreen$ = this.breakpointObserver
      .observe([Breakpoints.Web, Breakpoints.Medium])
      .pipe(map(({ matches }) => matches));
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
