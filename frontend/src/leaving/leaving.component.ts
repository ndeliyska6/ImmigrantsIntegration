import {Component, OnInit} from '@angular/core';
import {MatAccordion, MatExpansionModule, MatExpansionPanel, MatExpansionPanelTitle} from "@angular/material/expansion";
import {NgForOf, NgIf} from "@angular/common";
import {MatSlideToggle} from "@angular/material/slide-toggle";
import {MatFormField, MatLabel} from "@angular/material/form-field";
import {MatInput} from "@angular/material/input";
import {MatButton} from "@angular/material/button";
import {RoomService} from "../services/room.service";
import {ImmigrantService} from "../services/immigrant.service";
import {Building} from "../models/building";
import {Room} from "../models/room";
import {BuildingService} from "../services/building.service";
import {MatOption, MatSelect} from "@angular/material/select";
import {FormBuilder} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {catchError, map, of, tap} from "rxjs";
import {LeavingImmigrant} from "../models/LeavingImmigrant";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-leaving',
  standalone: true,
  imports: [
    MatAccordion,
    MatExpansionPanel,
    MatExpansionPanelTitle,
    NgForOf,
    MatSlideToggle,
    NgIf,
    MatFormField,
    MatInput,
    MatButton,
    MatSelect,
    MatOption,
    MatLabel,
    MatExpansionModule
  ],
  templateUrl: './leaving.component.html',
  styleUrl: './leaving.component.css'
})

export class LeavingComponent implements OnInit {
  addresses: string[] = [];
  rooms: Room[] = [];
  visibilityMap: Map<number, boolean> = new Map<number, boolean>();
  leavingDateMap: Map<number, string> = new Map<number, string>();



  constructor(
    private roomService: RoomService,
    private immigrantService: ImmigrantService,
    private activatedRoute: ActivatedRoute,
    private snackBar: MatSnackBar,
    private router: Router
  ) {
  }

  ngOnInit() {
    this.activatedRoute.data.pipe(map(data => data['buildings'])).subscribe(buildings => {
      this.addresses = buildings.map((building: Building) => building.buildingAddress);
    })
  }

  toggleInput(id: number, $event: any) {
    this.visibilityMap.set(id, $event.checked);
    if ($event.checked == false) {
      this.leavingDateMap.delete(id);
    }
  }

  toggleDate(id: number, $event: any) {
    const inputValue = $event.target as HTMLInputElement
    this.leavingDateMap.set(id, inputValue.value)
  }

  getRoomsByAddress(address: string) {
    this.roomService.getRoomByAddress(address).subscribe(rooms => {
      this.rooms = rooms;
    });
  }

  markAsLeft() {
    const leavingImmigrant: LeavingImmigrant[] = []
    this.leavingDateMap.forEach((value, key) => {
      leavingImmigrant.push(new LeavingImmigrant(key, value))
    })
    this.immigrantService.updateIfLeaving(leavingImmigrant).pipe(
      tap(() => {
        this.snackBar.open('Leaving successful!', 'Close', { duration: 3000 });
      }),
      catchError((error) => {
        console.error('Error registering immigrant:', error);
        this.snackBar.open('Leaving failed!', 'Close', { duration: 3000 });
        return of(null);
      })
    )
      .subscribe()
  }

  navigateTo(url: string) {
    this.router.navigate([url])
  }
}

