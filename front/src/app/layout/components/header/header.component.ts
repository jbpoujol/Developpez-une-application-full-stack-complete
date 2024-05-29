import { Component, OnInit } from '@angular/core';
import { MenuItem } from 'primeng/api';

/**
 * HeaderComponent is responsible for displaying the application header with navigation links.
 */
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
})
export class HeaderComponent implements OnInit {
  /** Array of menu items for the header navigation */
  items!: MenuItem[];

  /**
   * Initializes the component by setting up the menu items.
   */
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
