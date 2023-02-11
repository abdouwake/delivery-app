import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  RouterStateSnapshot,
  UrlTree,
} from '@angular/router';
import { Observable } from 'rxjs';
import { EntryPointAppService } from '../services/entry-point-app.service';
import { AppCookieService } from '../services/Utilities/app-cookie.service';
import { JWTTokenService } from '../services/Utilities/jwttoken.service';

@Injectable({
  providedIn: 'root',
})
export class AuthorizeGuardService implements CanActivate {
  constructor(
    private appCookieService: AppCookieService,
    private loginService: EntryPointAppService,
    private jwtService: JWTTokenService
  ) {}
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ):
    | boolean
    | UrlTree
    | Observable<boolean | UrlTree>
    | Promise<boolean | UrlTree> {
    if (this.jwtService.getUser()) {
      if (this.jwtService.isTokenExpired()) {
        this.loginService.redirectToLogin();
      } else {
        return true;
      }
    } else {
      this.loginService.redirectToLogin();
    }
    throw new Error('Method not implemented.');
  }
}
