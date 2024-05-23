import { Component } from '@angular/core';
import { Layouts } from './layout/enums/Layouts.enum';
import { Router, RoutesRecognized } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent {
  Layouts = Layouts;

  layout!: Layouts;

  constructor(private router: Router) {
    this.router.events.subscribe((data) => {
      if (data instanceof RoutesRecognized) {
        this.layout = data?.state?.root?.firstChild?.data?.['layout'];
      }
    });
  }
}
