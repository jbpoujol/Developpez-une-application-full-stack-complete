import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ProfileService } from '../../services/profile.service';
import { User, AuthService } from '@auth';
import { Subject, takeUntil } from 'rxjs';

@Component({
  selector: 'app-profile-form',
  templateUrl: './profile-form.component.html',
  styleUrls: ['./profile-form.component.scss'],
})
export class ProfileFormComponent implements OnInit, OnDestroy {
  profileForm!: FormGroup;

  currentUser?: User | null;

  destroy$ = new Subject<boolean>();

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private profileService: ProfileService
  ) {}

  ngOnInit(): void {
    this.profileForm = this.fb.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
    });

    this.profileService
      .loadCurrentUser()
      .pipe(takeUntil(this.destroy$))
      .subscribe((user) => {
        this.currentUser = user;
        if (user) {
          this.profileForm.patchValue({
            name: user.name,
            email: user.email,
          });
        }
      });
  }

  onSave(): void {
    if (this.profileForm.valid) {
      this.profileService
        .updateProfile(this.profileForm.value)
        .pipe(takeUntil(this.destroy$))
        .subscribe({
          next: () => {
            console.log('Profile updated successfully');
          },
          error: (error) => {
            console.error('Failed to update profile', error);
          },
        });
    }
  }

  onLogout(): void {
    this.authService.logout();
  }

  ngOnDestroy(): void {
    this.destroy$.next(true);
    this.destroy$.complete();
  }
}
