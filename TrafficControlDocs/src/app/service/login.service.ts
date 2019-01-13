import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";

const BASE_CITY_URL = "http://localhost:8082/trafficcontrolanddetection/city";

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  constructor(
    private http: HttpClient
  ) { }

  public authenticate(username: string, password: string): Promise<any>{
    username = username == undefined ? " " : username.length == 0 ? " " : username;
    password = password == undefined ? " " : password.length == 0 ? " " : password;

    return this.http.get(BASE_CITY_URL, {
      headers: {
        Authorization: 'Basic ' + btoa(username + ":" + password)
      }
    }).toPromise();
  }
}
