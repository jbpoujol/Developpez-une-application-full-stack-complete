import { AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';

/**
 * PasswordValidator provides custom validators for password strength.
 */
export class PasswordValidator {
  /**
   * Validator that checks if a password is strong.
   * A strong password must contain at least one number, one uppercase letter,
   * one lowercase letter, one special character, and be at least 8 characters long.
   * @returns A ValidatorFn that returns a ValidationErrors object if the validation fails, otherwise null
   */
  static strongPassword(): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      const value = control.value;

      if (!value) {
        return null;
      }

      const hasNumber = /[0-9]/.test(value);
      const hasUpper = /[A-Z]/.test(value);
      const hasLower = /[a-z]/.test(value);
      const hasSpecial = /[!@#$%^&*(),.?":{}|<>]/.test(value);
      const hasMinimumLength = value.length >= 8;

      const passwordValid =
        hasNumber && hasUpper && hasLower && hasSpecial && hasMinimumLength;

      return !passwordValid ? { strongPassword: true } : null;
    };
  }
}
