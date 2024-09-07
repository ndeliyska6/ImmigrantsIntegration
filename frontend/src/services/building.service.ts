import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Room} from "../models/room";
import {Building} from "../models/building";

@Injectable({
  providedIn: 'root'
})
export class BuildingService {

  private apiUrl = 'http://localhost:8080/building'; // Adjust to your backend API endpoint

  constructor(private http: HttpClient) { }

  getAllBuildings(): Observable<Building[]> {
    return this.http.get<Building[]>(`${this.apiUrl}`);
  }

  // Save a new building
  saveBuilding(building: Building): Observable<Building> {
    return this.http.post<Building>(this.apiUrl, building);
  }

  // Find a building by its ID
  getBuildingById(id: number): Observable<Building> {
    return this.http.get<Building>(`${this.apiUrl}/${id}`);
  }

  // Find a building by its address
  getBuildingByAddress(buildingAddress: string): Observable<Building> {
    return this.http.get<Building>(`${this.apiUrl}/address/${encodeURIComponent(buildingAddress)}`);
  }


  // Fetch available rooms for a particular building by address
  getAvailableRooms(buildingAddress: string): Observable<Room[]> {
    return this.http.get<Room[]>(`${this.apiUrl}/${encodeURIComponent(buildingAddress)}/rooms/available`);
  }

}
