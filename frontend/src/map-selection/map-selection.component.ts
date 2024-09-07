import {AfterViewInit, Component, OnInit} from '@angular/core';
import {ImmigrantService} from "../services/immigrant.service";
import {NgForOf, NgIf} from "@angular/common";
import {MatFormField, MatLabel} from "@angular/material/form-field";
import {MatOption, MatSelect} from "@angular/material/select";
import {MatButton} from "@angular/material/button";
import * as L from 'leaflet';
import {Building} from "../models/building";
import {Room} from "../models/room";
import {MatSnackBar} from "@angular/material/snack-bar";
import {catchError, of, tap} from "rxjs";
import {BuildingService} from "../services/building.service";
import 'leaflet-extra-markers';  // Import Leaflet ExtraMarkers
import 'leaflet-extra-markers/dist/css/leaflet.extra-markers.min.css';

@Component({
  selector: 'app-map-selection',
  standalone: true,
  imports: [
    NgIf,
    MatFormField,
    MatSelect,
    MatOption,
    NgForOf,
    MatButton,
    MatLabel
  ],
  templateUrl: './map-selection.component.html',
  styleUrl: './map-selection.component.css'
})
export class MapSelectionComponent implements OnInit, AfterViewInit{
  private map!: L.Map;
  buildings: any[] = [];
  selectedBuilding!: Building;
  availableRooms: Room[] = [];
  selectedRoomNumber!: number;


  constructor(
    private buildingService: BuildingService,
    private immigrantService: ImmigrantService,
    private snackBar: MatSnackBar
  ) {
  }

  ngOnInit() {
  }

  ngAfterViewInit(): void {
    this.initializeMap();
    this.loadBuildings();
  }

  private initializeMap(): void {
    this.map = L.map('map').setView([42.7339, 25.4858], 7);
    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      maxZoom: 18,
      attribution: '© OpenStreetMap contributors'
    }).addTo(this.map);
  }

  private loadBuildings(): void {
    this.buildingService.getAllBuildings().subscribe((data: Building[]) => {
      this.buildings = data;
      this.addMarkersToMap();
    });
  }

  private addMarkersToMap(): void {
    const redMarker = L.ExtraMarkers.icon({
      icon: 'fa-number',
      number: '',
      markerColor: 'red',
      shape: 'circle',
      prefix: 'fa'
    });
    const greenMarker = L.ExtraMarkers.icon({
      icon: 'fa-number',
      number: '',
      markerColor: 'green',
      shape: 'circle',
      prefix: 'fa'
    });
    this.buildings.forEach((building: Building) => {
      const marker = L.marker([building.latitude, building.longitude], { icon: building.buildingStatus != 'AVAILABLE' ? redMarker : greenMarker })
        .addTo(this.map)
        .bindPopup(building.buildingAddress);

      marker.on('click', () => {
        this.onMarkerClick(building);
      });
    });
  }

  onMarkerClick(building: Building): void {
    this.selectedBuilding = building;
    this.buildingService.getAvailableRooms(building.buildingAddress).subscribe((data: Room[]) => {
      this.availableRooms = data;
    });
  }

  onRoomSelect(room: Room): void {
    this.selectedRoomNumber = room.roomNumber;
  }

  finalizeRegistration(): void {
    this.immigrantService.registerImmigrant(this.selectedRoomNumber, this.selectedBuilding.buildingAddress).pipe(
      tap(() => {
        this.snackBar.open('Registration successful!', 'Close', { duration: 3000 });
      }),
      catchError((error) => {
        console.error('Error registering immigrant:', error);
        this.snackBar.open('Registration failed!', 'Close', { duration: 3000 });
        return of(null);
      })
    )
      .subscribe();
  }
}
