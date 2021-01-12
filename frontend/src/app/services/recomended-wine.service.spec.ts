import { TestBed } from '@angular/core/testing';

import { RecomendedWineService } from './recomended-wine.service';

describe('RecomendedWineService', () => {
  let service: RecomendedWineService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RecomendedWineService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
