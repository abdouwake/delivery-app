import { Component, OnInit } from '@angular/core';
import { map, Observable } from 'rxjs';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { RestaurantService } from '../../services/restaurant.service';

@Component({
  selector: 'app-landing-page',
  templateUrl: './landing-page.component.html',
  styleUrls: ['./landing-page.component.css'],
})
export class LandingPageComponent implements OnInit {
  isSmallScreen$: Observable<boolean> | undefined;
  isLargeScreen$: Observable<boolean> | undefined;

  constructor(private breakpointObserver: BreakpointObserver) {}

  ngOnInit(): void {
    this.isSmallScreen$ = this.breakpointObserver
      .observe([Breakpoints.Small, Breakpoints.XSmall])
      .pipe(map(({ matches }) => matches));

    this.isLargeScreen$ = this.breakpointObserver
      .observe([Breakpoints.Web, Breakpoints.Medium])
      .pipe(map(({ matches }) => matches));
  }
}
