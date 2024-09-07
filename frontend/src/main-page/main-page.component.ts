import { Component } from '@angular/core';
import {MatButton} from "@angular/material/button";
import {MatStepperNext} from "@angular/material/stepper";
import {Router, RouterOutlet} from "@angular/router";
import {FormBuilder} from "@angular/forms";

@Component({
  selector: 'app-main-page',
  standalone: true,
  imports: [
    MatButton,
    MatStepperNext,
    RouterOutlet
  ],
  templateUrl: './main-page.component.html',
  styleUrl: './main-page.component.css'
})
export class MainPageComponent {
  constructor(private router: Router) {
  }



}
