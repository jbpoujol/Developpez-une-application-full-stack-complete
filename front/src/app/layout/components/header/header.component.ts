import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
})
export class HeaderComponent implements OnInit {
  items!: MenuItem[];

  ngOnInit(): void {
    this.items = [
      {
        label: 'Articles',
        routerLink: ['/articles'],
      },
      {
        label: 'Thèmes',
        routerLink: ['/themes'],
      },
      {
        icon: 'pi pi-fw pi-user',
        routerLink: ['/profile'],
      },
    ];
  }
}
