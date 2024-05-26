import { Component, EventEmitter, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-comment-form',
  templateUrl: './comment-form.component.html',
  styleUrls: ['./comment-form.component.scss'],
})
export class CommentFormComponent {
  @Output() submitComment = new EventEmitter<string>();
  commentForm: FormGroup;

  constructor(private fb: FormBuilder) {
    this.commentForm = this.fb.group({
      content: ['', Validators.required],
    });
  }

  onSubmit(): void {
    if (this.commentForm.valid) {
      this.submitComment.emit(this.commentForm.value.content);
      this.commentForm.reset();
    }
  }
}
