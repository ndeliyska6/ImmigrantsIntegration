import {BuildingStatus} from "../enums/building-status";

export class Building {
id: number;
buildingAddress: string;
latitude: number;
longitude: number;
buildingStatus: BuildingStatus;

  constructor(
    id: number,
    buildingAddress: string,
    latitude: number,
    longitude: number,
    buildingStatus: BuildingStatus
  ) {
    this.id = id;
    this.buildingAddress = buildingAddress;
    this.latitude = latitude;
    this.longitude = longitude;
    this.buildingStatus = buildingStatus;
  }

}
