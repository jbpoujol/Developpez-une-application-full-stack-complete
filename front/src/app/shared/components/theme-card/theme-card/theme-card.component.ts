import {
  ChangeDetectionStrategy,
  Component,
  EventEmitter,
  Input,
  Output,
} from '@angular/core';
import { Theme } from '../../../models';

/**
 * ThemeCardComponent is responsible for displaying a theme card.
 * It allows the user to subscribe to the theme.
 */
@Component({
  selector: 'app-theme-card',
  templateUrl: './theme-card.component.html',
  styleUrls: ['./theme-card.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ThemeCardComponent {
  /** The page context where the theme card is used */
  @Input() page = '';
  /** The theme data to be displayed */
  @Input() theme!: Theme;
  /** Event emitter to notify parent component about subscription action */
  @Output() subscribe = new EventEmitter<number>();

  /**
   * Handles the subscribe action by emitting the theme ID.
   */
  onSubscribe(): void {
    this.subscribe.emit(this.theme.id);
  }
}
