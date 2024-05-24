import { Component, OnInit } from '@angular/core';
import { Layouts } from './layout/enums/Layouts.enum';
import { Router, RoutesRecognized } from '@angular/router';
import { AuthService } from './auth/services/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent implements OnInit {
  Layouts = Layouts;

  layout!: Layouts;

  constructor(private router: Router, private authService: AuthService) {
    this.router.events.subscribe((data) => {
      if (data instanceof RoutesRecognized) {
        this.layout = data?.state?.root?.firstChild?.data?.['layout'];
      }
    });
  }

  ngOnInit() {
    this.authService.init();
  }
}
