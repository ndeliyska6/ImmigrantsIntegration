import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Building} from "../models/building";
import {LeavingImmigrant} from "../models/LeavingImmigrant";
import {Immigrant} from "../models/immigrant";

@Injectable({
  providedIn: 'root'
})
export class ImmigrantService {

  private apiUrl = 'http://localhost:8080/immigrant';
  immigrantInfo: any;

  constructor(private http: HttpClient) {}

  setImmigrantInfo(info: any) {
    this.immigrantInfo = info;
  }

  getImmigrantInfo() {
    return this.immigrantInfo;
  }

  registerImmigrant(roomNumber: number, buildingAddress: string): Observable<any> {
    const immigrant = {
      ...this.immigrantInfo,
      room: {
        roomNumber: roomNumber,
        building: {
          buildingAddress: buildingAddress
        }
      }};
    return this.http.post(`${this.apiUrl}/register`, immigrant);
  }

  updateIfLeaving(leavingImmigrant: LeavingImmigrant[]): Observable<LeavingImmigrant[]> {
    return this.http.put<LeavingImmigrant[]>("http://localhost:8080/immigrant/leaving-date", leavingImmigrant);
  }
}


