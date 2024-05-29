import { Component, EventEmitter, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

/**
 * CommentFormComponent is responsible for providing a form to submit comments.
 * It emits an event when a comment is submitted.
 */
@Component({
  selector: 'app-comment-form',
  templateUrl: './comment-form.component.html',
  styleUrls: ['./comment-form.component.scss'],
})
export class CommentFormComponent {
  /** Event emitter that emits the comment content when the form is submitted */
  @Output() submitComment = new EventEmitter<string>();
  /** The form group for the comment form */
  commentForm: FormGroup;

  /**
   * Creates an instance of CommentFormComponent.
   * @param fb - The form builder used to create the form group
   */
  constructor(private fb: FormBuilder) {
    this.commentForm = this.fb.group({
      content: ['', Validators.required],
    });
  }

  /**
   * Handles the form submission.
   * If the form is valid, emits the comment content and resets the form.
   */
  onSubmit(): void {
    if (this.commentForm.valid) {
      this.submitComment.emit(this.commentForm.value.content);
      this.commentForm.reset();
    }
  }
}
