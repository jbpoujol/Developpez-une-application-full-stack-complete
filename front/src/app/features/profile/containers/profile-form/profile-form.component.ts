import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ProfileService } from '../../services/profile.service';
import { User, AuthService } from '@auth';
import { Subject, takeUntil } from 'rxjs';
import { MessageService } from 'primeng/api';

/**
 * ProfileFormComponent is responsible for displaying and managing the user profile form.
 * It allows the user to update their profile information and logout.
 */
@Component({
  selector: 'app-profile-form',
  templateUrl: './profile-form.component.html',
  styleUrls: ['./profile-form.component.scss'],
})
export class ProfileFormComponent implements OnInit, OnDestroy {
  /** The form group for the profile form */
  profileForm!: FormGroup;
  /** The current user */
  currentUser?: User | null;
  /** Subject used to unsubscribe from observables when the component is destroyed */
  private destroy$ = new Subject<boolean>();

  /**
   * Creates an instance of ProfileFormComponent.
   * @param fb - The form builder used to create the form group
   * @param authService - The authentication service used for logging out
   * @param profileService - The profile service used to load and update the user profile
   * @param messageService - The message service used to display toast notifications
   */
  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private profileService: ProfileService,
    private messageService: MessageService
  ) {}

  /**
   * Initializes the component by creating the profile form group and loading the current user profile.
   */
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

  /**
   * Handles the save action for the profile form.
   * Updates the user profile if the form is valid.
   * Displays a success toast notification on success and an error toast notification on failure.
   */
  onSave(): void {
    if (this.profileForm.valid) {
      this.profileService
        .updateProfile(this.profileForm.value)
        .pipe(takeUntil(this.destroy$))
        .subscribe({
          next: () => {
            this.messageService.add({
              severity: 'success',
              summary: 'Success',
              detail: 'Profile updated successfully',
            });
          },
          error: (error) => {
            this.messageService.add({
              severity: 'error',
              summary: 'Error',
              detail: 'Failed to update profile',
            });
          },
        });
    }
  }

  /**
   * Handles the logout action by calling the authentication service's logout method.
   */
  onLogout(): void {
    this.authService.logout();
  }

  /**
   * Cleans up the component by unsubscribing from observables.
   */
  ngOnDestroy(): void {
    this.destroy$.next(true);
    this.destroy$.complete();
  }
}
