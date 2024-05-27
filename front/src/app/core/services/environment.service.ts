import { Injectable } from '@angular/core';
import { Environment, environment } from '@environment';

@Injectable({
  providedIn: 'root',
})
export class EnvironmentService implements Environment {
  get production() {
    return environment.production;
  }

  get apiUrl() {
    return environment.apiUrl;
  }
}
