import { TestBed } from '@angular/core/testing';

import { MbtiService } from './mbti.service';

describe('MbtiService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: MbtiService = TestBed.get(MbtiService);
    expect(service).toBeTruthy();
  });
});
