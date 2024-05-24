import {
  ChangeDetectionStrategy,
  Component,
  EventEmitter,
  Input,
  Output,
} from '@angular/core';
import { Theme } from '../../../models';

@Component({
  selector: 'app-theme-card',
  templateUrl: './theme-card.component.html',
  styleUrls: ['./theme-card.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ThemeCardComponent {
  @Input() page = '';
  @Input() theme!: Theme;
  @Output() subscribe = new EventEmitter<number>();

  onSubscribe() {
    this.subscribe.emit(this.theme.id);
  }
}
