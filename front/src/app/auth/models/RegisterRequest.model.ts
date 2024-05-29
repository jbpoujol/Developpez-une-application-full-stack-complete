/**
 * Interface representing the structure of a registration request.
 */
export interface RegisterRequest {
  /** The name of the user */
  name: string;
  /** The email address of the user */
  email: string;
  /** The password of the user */
  password: string;
}
