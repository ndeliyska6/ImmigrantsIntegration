import {Room} from "./room";
import {ImmigrantStatus} from "../enums/immigrant-status";

export class Immigrant{
  id: number;
  name: string;
  nationality: string;
  gender: string;
  age: number;
  entryDate: Date;
  leavingDate: Date | null;
  room: Room;
  status: ImmigrantStatus;

  constructor(id: number, name: string, nationality: string, gender: string, age: number, entryDate: Date, leavingDate: Date, room: Room, status: ImmigrantStatus) {
    this.id = id;
    this.name = name;
    this.nationality = nationality;
    this.gender = gender;
    this.age = age;
    this.entryDate = entryDate;
    this.leavingDate = leavingDate;
    this.room = room;
    this.status = status;
  }
}
