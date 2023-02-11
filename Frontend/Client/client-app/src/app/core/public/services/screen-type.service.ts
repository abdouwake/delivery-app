import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';

@Injectable({
  providedIn: 'root',
})
export class ScreenTypeService {
  isSmallScreen$: Observable<boolean>;
  isMediumScreen$: Observable<boolean>;
  isLargeScreen$: Observable<boolean>;

  constructor(private breakpointObserver: BreakpointObserver) {
    this.isSmallScreen$ = this.breakpointObserver
      .observe([Breakpoints.XSmall])
      .pipe(map(({ matches }) => matches));

    this.isMediumScreen$ = this.breakpointObserver
      .observe([Breakpoints.Small, Breakpoints.Medium])
      .pipe(map(({ matches }) => matches));

    this.isLargeScreen$ = this.breakpointObserver
      .observe([Breakpoints.Web])
      .pipe(map(({ matches }) => matches));
  }
}
