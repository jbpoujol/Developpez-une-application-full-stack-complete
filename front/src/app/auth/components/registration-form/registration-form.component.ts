import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subject, takeUntil } from 'rxjs';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { PasswordValidator } from '../../validators/password.validator';

@Component({
  selector: 'app-registration-form',
  templateUrl: './registration-form.component.html',
  styleUrls: ['./registration-form.component.scss'],
})
export class RegistrationFormComponent implements OnInit, OnDestroy {
  registrationForm!: FormGroup;

  destroy$ = new Subject<boolean>();

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.registrationForm = this.fb.group({
      name: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, PasswordValidator.strongPassword()]],
    });
  }

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

  ngOnDestroy(): void {
    this.destroy$.next(true);
    this.destroy$.unsubscribe();
  }
}
