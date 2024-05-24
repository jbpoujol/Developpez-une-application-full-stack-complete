import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Theme } from '../../models/Theme.model';

@Component({
  selector: 'app-theme-card',
  templateUrl: './theme-card.component.html',
  styleUrls: ['./theme-card.component.scss'],
})
export class ThemeCardComponent {
  @Input() theme!: Theme;
  @Output() subscribe = new EventEmitter<number>();

  onSubscribe() {
    this.subscribe.emit(this.theme.id);
  }
}
