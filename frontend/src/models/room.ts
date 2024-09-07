import {Building} from "./building";
import {Immigrant} from "./immigrant";
import {RoomStatus} from "../enums/room-status";


export class Room {
  id: number;
  roomNumber: number;
  roomCapacity: number;
  building: Building;
  immigrants: Immigrant[];
  roomStatus: RoomStatus;

  constructor(id: number, roomNumber: number, roomCapacity: number, building: Building, immigrants: Immigrant[], roomStatus: RoomStatus) {
    this.id = id;
    this.roomNumber = roomNumber;
    this.roomCapacity = roomCapacity;
    this.building = building;
    this.immigrants = immigrants;
    this.roomStatus = roomStatus;
  }


}

