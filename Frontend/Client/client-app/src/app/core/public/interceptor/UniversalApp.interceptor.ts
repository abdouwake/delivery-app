import { Injectable, Inject, Optional } from '@angular/core';
import {
  HttpInterceptor,
  HttpHandler,
  HttpRequest,
} from '@angular/common/http';
import { AppCookieService } from '../services/Utilities/app-cookie.service';
@Injectable()
export class UniversalAppInterceptor implements HttpInterceptor {
  constructor(private authService: AppCookieService) {}

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    const token = this.authService.get('tokenAppDeliveryClient');
    if (req.url.includes('/auth')) {
      return next.handle(req);
    }
    req = req.clone({
      url: req.url,
      setHeaders: {
        Authorization: `Bearer ${token}`,
      },
    });
    return next.handle(req);
  }
}
