import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { map, Observable } from 'rxjs';
import { Router } from '@angular/router';
import { ScreenTypeService } from '../../services/screen-type.service';
import { EntryPointAppService } from '../../services/entry-point-app.service';
import { LoginDto } from '../../models/login.dto';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  isSmallScreen$: Observable<boolean> | undefined;
  isMediumScreen$: Observable<boolean> | undefined;
  isLargeScreen$: Observable<boolean> | undefined;

  email: string = '';
  password: string = '';

  constructor(
    private breakpointObserver: BreakpointObserver,
    private router: Router,
    private signInService: EntryPointAppService
  ) {}

  public ngOnInit(): void {
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

  public redirect(): void {
    this.router.navigate(['/singnup']);
  }

  login() {
    if (this.email.length != 0 && this.password.length != 0) {
      var loginDto: LoginDto = new LoginDto(this.email, this.password);
      this.signInService.authenticate(loginDto);
    } else alert('Creds are mendatory !');
  }
}
