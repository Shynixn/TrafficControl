import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

const BASE_URL = "http://localhost:8083/roadmaintenance/staff";

@Injectable({
  providedIn: 'root'
})
export class StaffMemberService {

  constructor(
    private http: HttpClient
  ) { }

  public retrieveStaffMembers(): Promise<any>{
    return this.http.get(BASE_URL).toPromise();
  }
}
