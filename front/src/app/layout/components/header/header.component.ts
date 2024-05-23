import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MenuItem } from 'primeng/api';
import { AuthService } from 'src/app/auth/services/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
})
export class HeaderComponent implements OnInit {
  items!: MenuItem[];

  constructor(private router: Router, private authService: AuthService) {}

  ngOnInit(): void {
    this.items = [
      {
        label: 'Articles',
        command: () => {
          this.router.navigate(['/articles']);
        },
      },
      {
        label: 'Thèmes',
        command: () => {
          this.router.navigate(['/themes']);
        },
      },
      {
        icon: 'pi pi-fw pi-user',
        items: [
          {
            label: 'Profile',
            icon: 'pi pi-fw pi-user',
            command: () => {
              this.router.navigate(['/profile']);
            },
          },
          {
            label: 'Quit',
            icon: 'pi pi-fw pi-power-off',
            command: () => {
              this.authService.logout();
            },
          },
        ],
      },
    ];
  }
}
