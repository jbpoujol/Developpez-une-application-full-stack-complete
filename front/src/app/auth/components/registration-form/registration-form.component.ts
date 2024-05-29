import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subject, takeUntil } from 'rxjs';
import { Router } from '@angular/router';
import { AuthService } from '../../services';
import { PasswordValidator } from '../../validators/password.validator';

/**
 * RegistrationFormComponent is responsible for displaying and managing the registration form.
 */
@Component({
  selector: 'app-registration-form',
  templateUrl: './registration-form.component.html',
  styleUrls: ['./registration-form.component.scss'],
})
export class RegistrationFormComponent implements OnInit, OnDestroy {
  /** The form group for the registration form */
  registrationForm!: FormGroup;

  /** Subject used to unsubscribe from observables when the component is destroyed */
  destroy$ = new Subject<boolean>();

  /**
   * Creates an instance of RegistrationFormComponent.
   * @param fb - FormBuilder for creating the form group
   * @param authService - AuthService for handling registration
   * @param router - Router for navigation
   */
  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {}

  /**
   * Initializes the component by creating the registration form group.
   */
  ngOnInit(): void {
    this.registrationForm = this.fb.group({
      name: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, PasswordValidator.strongPassword()]],
    });
  }

  /**
   * Handles the form submission.
   * If the form is valid, it triggers the registration process.
   */
  onSubmit(): void {
    if (this.registrationForm.valid) {
      this.authService
        .register({
          name: this.registrationForm.value.name,
          email: this.registrationForm.value.email,
          password: this.registrationForm.value.password,
        })
        .pipe(takeUntil(this.destroy$))
        .subscribe({
          next: () => {
            this.router.navigate(['profile']);
          },
          error: (error: HttpErrorResponse) => {
            console.error('Registration failed', error.error.message);
          },
        });
    }
  }

  /**
   * Cleans up the component by unsubscribing from observables.
   */
  ngOnDestroy(): void {
    this.destroy$.next(true);
    this.destroy$.unsubscribe();
  }
}
