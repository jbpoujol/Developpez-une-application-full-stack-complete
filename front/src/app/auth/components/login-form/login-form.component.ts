import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../services';
import { Router } from '@angular/router';
import { Subject, takeUntil } from 'rxjs';

/**
 * LoginFormComponent is responsible for displaying and managing the login form.
 */
@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.scss'],
})
export class LoginFormComponent implements OnInit, OnDestroy {
  /** The form group for the login form */
  loginForm!: FormGroup;

  /** Subject used to unsubscribe from observables when the component is destroyed */
  destroy$ = new Subject<boolean>();

  /**
   * Creates an instance of LoginFormComponent.
   * @param fb - FormBuilder for creating the form group
   * @param authService - AuthService for handling authentication
   * @param router - Router for navigation
   */
  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {}

  /**
   * Initializes the component by creating the login form group.
   */
  ngOnInit(): void {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]],
    });
  }

  /**
   * Handles the form submission.
   * If the form is valid, it triggers the login process.
   */
  onSubmit(): void {
    if (this.loginForm.valid) {
      this.authService
        .login({
          email: this.loginForm.value.email,
          password: this.loginForm.value.password,
        })
        .pipe(takeUntil(this.destroy$))
        .subscribe({
          next: () => {
            this.router.navigate(['profile']);
          },
          error: (error) => {
            console.error('Login failed', error);
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
