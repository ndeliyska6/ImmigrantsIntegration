import { ResolveFn } from '@angular/router';
import {inject} from "@angular/core";
import {BuildingService} from "../services/building.service";

export const leavingResolver: ResolveFn<Object> = (route, state) => {
  return inject(BuildingService).getAllBuildings();
};
