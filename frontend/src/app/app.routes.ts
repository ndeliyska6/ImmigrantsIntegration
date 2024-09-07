import { Routes } from '@angular/router';
import {LoginComponent} from "../login/login.component";
import {ImmigrantRegistrationComponent} from "../immigrant-registration/immigrant-registration.component";
import {MapSelectionComponent} from "../map-selection/map-selection.component";
import {resolve} from "@angular/compiler-cli";
import {LeavingComponent} from "../leaving/leaving.component";
import {leavingResolver} from "../leaving/leaving.resolver";

export const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },  // Default route
  { path: 'login', component: LoginComponent },
  { path: 'register', component: ImmigrantRegistrationComponent},
  { path: 'map-selection', component: MapSelectionComponent},
  { path: 'leaving', component: LeavingComponent ,resolve: {buildings: leavingResolver} }
];
