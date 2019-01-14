import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

const BASE_URL = "http://localhost:8083/roadmaintenance/incident";

@Injectable({
  providedIn: 'root'
})
export class IncidentService {

  constructor(
    private http: HttpClient
  ) { }

  public requestIncidents(): Promise<object[]>{
    return this.http.get<object[]>(
      BASE_URL
    ).toPromise();
  }

  public sendIncident(values: object): Promise<any>{
    return this.http.post(BASE_URL, values, {
      headers: {
        "Content-Type": "application/json"
      }
    })
      .toPromise();
  }
}
