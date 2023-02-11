import { Injectable, OnInit } from '@angular/core';
import jwt_decode from 'jwt-decode';
import { AppCookieService } from './app-cookie.service';

@Injectable({
  providedIn: 'root',
})
export class JWTTokenService implements OnInit {
  public ngOnInit(): void {
    this.jwtToken = this.cookies.get(this.TOKEN_ITEM_COOKIE_NAME);
  }
  TOKEN_ITEM_COOKIE_NAME: string = 'tokenAppDeliveryClient';

  jwtToken: string = this.cookies.get(this.TOKEN_ITEM_COOKIE_NAME);
  decodedToken: any;

  constructor(private cookies: AppCookieService) {
    this.jwtToken = this.cookies.get(this.TOKEN_ITEM_COOKIE_NAME);
  }

  setToken(token: string) {
    if (token) {
      this.jwtToken = token;
      this.cookies.set(this.TOKEN_ITEM_COOKIE_NAME, token);
    }
  }

  decodeToken() {
    this.decodedToken = jwt_decode(
      this.cookies.get(this.TOKEN_ITEM_COOKIE_NAME)
    );
  }

  getDecodeToken() {
    return jwt_decode(this.cookies.get(this.TOKEN_ITEM_COOKIE_NAME));
  }

  getUser() {
    this.decodeToken();
    return this.decodedToken ? this.decodedToken.sub : null;
  }

  getEmailId() {
    this.decodeToken();
    return this.decodedToken ? this.decodedToken.sub : null;
  }

  getExpiryTime() {
    this.decodeToken();
    return this.decodedToken ? this.decodedToken.exp : -1;
  }

  isTokenExpired(): boolean {
    var expiryTime: number = this.getExpiryTime();
    if (expiryTime) {
      return 1000 * expiryTime - new Date().getTime() < 5000;
    } else {
      return false;
    }
  }
}
