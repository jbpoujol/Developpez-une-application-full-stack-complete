/**
 * Interface representing the structure of an update profile request.
 */
export interface UpdateProfileRequest {
  /** The name of the user */
  name: string;
  /** The email address of the user */
  email: string;
}
