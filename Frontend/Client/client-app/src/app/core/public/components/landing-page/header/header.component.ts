import { Component, Input, OnInit } from '@angular/core';
import { map, Observable } from 'rxjs';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent implements OnInit {
  constructor(private breakpointObserver: BreakpointObserver) {}

  isSmallScreen$: Observable<boolean> | undefined;
  isLargeScreen$: Observable<boolean> | undefined;

  headerItems = [
    {
      key: 0,
      title: 'All restaurants',
      path: '/Restaurants',
      icon: 'store_mall_directory',
    },
    { key: 1, title: 'My orders', path: '/Orders', icon: 'bookmark_border' },
    { key: 2, title: 'My Adresses', path: '/Adresses', icon: 'place' },
  ];

  @Input() selectedItem = 0;

  ngOnInit(): void {
    this.isSmallScreen$ = this.breakpointObserver
      .observe([Breakpoints.Small, Breakpoints.XSmall])
      .pipe(map(({ matches }) => matches));

    this.isLargeScreen$ = this.breakpointObserver
      .observe([Breakpoints.Web])
      .pipe(map(({ matches }) => matches));
  }
}
