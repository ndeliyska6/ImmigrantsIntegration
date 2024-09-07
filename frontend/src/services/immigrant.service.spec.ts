import { TestBed } from '@angular/core/testing';

import { ImmigrantService } from './immigrant.service';

describe('ImmigrantService', () => {
  let service: ImmigrantService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ImmigrantService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
