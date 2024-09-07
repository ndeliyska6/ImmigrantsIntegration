import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Room} from "../models/room";
import {RoomStatus} from "../enums/room-status";

@Injectable({
  providedIn: 'root'
})
export class RoomService {

  private apiUrl = 'http://localhost:8080/room'; // Backend endpoint

  constructor(private http: HttpClient) { }

  // Fetch all rooms with accommodated immigrants
  getAllRooms(): Observable<Room[]> {
    return this.http.get<Room[]>(`${this.apiUrl}/get`);
  }

  // Save a new room and update the building status
  saveRoom(buildingId: number, room: Room): Observable<Room> {
    return this.http.post<Room>(`${this.apiUrl}/save/${buildingId}`, room);
  }

  // Find a room by ID
  getRoomById(id: number): Observable<Room> {
    return this.http.get<Room>(`${this.apiUrl}/${id}`);
  }

  // Find a room by room number
  getRoomByNumber(roomNumber: number): Observable<Room> {
    return this.http.get<Room>(`${this.apiUrl}/number/${roomNumber}`);
  }

  // Check the status of a room
  checkRoomStatus(id: number): Observable<RoomStatus> {
    return this.http.get<RoomStatus>(`${this.apiUrl}/${id}/status`);
  }

  getRoomByAddress(address: string): Observable<Room[]> {
    return this.http.get<Room[]>(`${this.apiUrl}/get-by-address/${address}`);
  }
}
