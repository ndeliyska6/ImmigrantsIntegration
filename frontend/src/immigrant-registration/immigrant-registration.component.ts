import { Component } from '@angular/core';
import {MatCard, MatCardContent, MatCardHeader, MatCardTitle} from "@angular/material/card";
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {MatFormField, MatLabel} from "@angular/material/form-field";
import {MatInput} from "@angular/material/input";
import {MatOption, MatSelect} from "@angular/material/select";
import {MatButton} from "@angular/material/button";
import {Router} from "@angular/router";
import {ImmigrantService} from "../services/immigrant.service";
import {MapSelectionComponent} from "../map-selection/map-selection.component";
import {NgIf} from "@angular/common";
import {MatStep, MatStepper, MatStepperNext} from "@angular/material/stepper";
import {MatSlideToggle} from "@angular/material/slide-toggle";

@Component({
  selector: 'app-immigrant-registration',
  standalone: true,
  imports: [
    MatCard,
    MatCardHeader,
    MatCardContent,
    ReactiveFormsModule,
    MatFormField,
    MatInput,
    MatSelect,
    MatOption,
    MatButton,
    MatLabel,
    MatCardTitle,
    MapSelectionComponent,
    NgIf,
    MatStepper,
    MatStep,
    MatStepperNext,
    MatSlideToggle
  ],
  templateUrl: './immigrant-registration.component.html',
  styleUrl: './immigrant-registration.component.css'
})
export class ImmigrantRegistrationComponent {
  immigrantForm: FormGroup;
  shouldOpenMap: boolean = false;

  constructor(
    private fb: FormBuilder,
    private immigrantService: ImmigrantService,
    private router: Router
  ) {
    this.immigrantForm = this.fb.group({
      name: ['', Validators.required],
      nationality: ['', Validators.required],
      gender: ['', Validators.required],
      age: ['', Validators.required],
      entryDate: ['', Validators.required]
    });
  }
  onSubmit(): void {
    if (this.immigrantForm.valid) {
      this.immigrantService.setImmigrantInfo(this.immigrantForm.value);
    }
  }
  navigateTo(url: string) {
    this.router.navigate([url])
  }
}
