import { TestBed } from '@angular/core/testing';

import { CocheInterceptorService } from './coche-interceptor.service';

describe('CocheInterceptorService', () => {
  let service: CocheInterceptorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CocheInterceptorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
