import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoginDto } from '../models/login.dto';
import { RegisterDto } from '../models/register.dto';
import { environment } from 'src/environments/environment';
import { ApiPaths } from './api-calls/ApiPaths';
import { Router } from '@angular/router';
import { JWTTokenService } from './Utilities/jwttoken.service';
import { ApiHttpServiceService } from './api-calls/api-http-service.service';
import { map } from 'rxjs';
@Injectable({
  providedIn: 'root',
})
export class EntryPointAppService {
  constructor(
    private http: HttpClient,
    private router: Router,
    private jWTTokenService: JWTTokenService,
    private clientApi: ApiHttpServiceService
  ) {}

  register(registerDto: RegisterDto) {
    this.clientApi.post(ApiPaths.SignIn, registerDto).subscribe((res: any) => {
      if (res.token != null) {
        this.jWTTokenService.setToken(res.token);
        this.router.navigateByUrl('/landing');
        return res;
      } else if (res.error.error != undefined) {
        alert(res.error.error.message);
      }
    });
  }

  authenticate(loginDto: LoginDto) {
    this.clientApi.post(ApiPaths.Login, loginDto).subscribe((res: any) => {
      if (res.token != null) {
        this.jWTTokenService.setToken(res.token);
        this.router.navigateByUrl('/landing');
        return res;
      } else if (res.error.error != undefined) {
        alert(res.error.error.message);
      }
    });
  }

  redirectToLogin() {
    this.router.navigateByUrl('/login');
  }
}
