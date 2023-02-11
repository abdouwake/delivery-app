import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class ApiHttpServiceService {
  constructor(private http: HttpClient) {}
  baseUrl = environment.baseUrl;

  headers = new HttpHeaders({
    'Content-Type': 'application/json',
  });

  //il faut que le path soit avec un slah
  public get(path: string) {
    return this.http.get(this.baseUrl + path, { headers: this.headers });
  }
  public post(path: string, data: any) {
    return this.http.post(this.baseUrl + path, data, { headers: this.headers });
  }
  public put(path: string, data: any) {
    return this.http.put(this.baseUrl + path, data, { headers: this.headers });
  }
  public delete(path: string) {
    return this.http.delete(this.baseUrl + path, {
      headers: this.headers,
    });
  }
}
