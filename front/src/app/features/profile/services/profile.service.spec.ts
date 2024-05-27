import { TestBed } from '@angular/core/testing';
import {
  HttpClientTestingModule,
  HttpTestingController,
} from '@angular/common/http/testing';
import { ProfileService } from './profile.service';
import { EnvironmentService } from '@core';
import { of } from 'rxjs';
import { User } from '@auth';

describe('ProfileService', () => {
  let service: ProfileService;
  let httpMock: HttpTestingController;
  let environmentService: EnvironmentService;

  beforeEach(() => {
    environmentService = {
      apiUrl: 'http://mock-api.com',
    } as EnvironmentService;

    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [
        ProfileService,
        { provide: EnvironmentService, useValue: environmentService },
      ],
    });

    service = TestBed.inject(ProfileService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should load current user if token exists', () => {
    const dummyUser: User = {
      id: 1,
      name: 'John Doe',
      email: 'john@example.com',
    };
    spyOn(localStorage, 'getItem').and.returnValue('mock-token');

    service.loadCurrentUser().subscribe((user) => {
      expect(user).toEqual(dummyUser);
    });

    const req = httpMock.expectOne(`${environmentService.apiUrl}/profile`);
    expect(req.request.method).toBe('GET');
    req.flush(dummyUser);
  });

  it('should return null if no token exists', () => {
    spyOn(localStorage, 'getItem').and.returnValue(null);

    service.loadCurrentUser().subscribe((user) => {
      expect(user).toBeNull();
    });

    httpMock.expectNone(`${environmentService.apiUrl}/profile`);
  });
});
