import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ImmigrantRegistrationComponent } from './immigrant-registration.component';

describe('ImmigrantRegistrationComponent', () => {
  let component: ImmigrantRegistrationComponent;
  let fixture: ComponentFixture<ImmigrantRegistrationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ImmigrantRegistrationComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ImmigrantRegistrationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
