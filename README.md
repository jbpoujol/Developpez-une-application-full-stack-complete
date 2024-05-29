# MDD

## Front : Angular Project

## Description

This is an Angular project that includes functionalities such as user authentication, article management, and theme management. The application uses PrimeNG for UI components, PrimeFlex for responsive grid layout, and PrimeIcons for icons.

## Features

- User authentication with login and registration
- Article creation, display, and commenting
- Theme subscription and management
- PrimeNG components for UI elements
- PrimeFlex for responsive layout
- PrimeIcons for icons

## Installation

1. Clone the repository:

   ```sh
   git clone git@github.com:jbpoujol/Developpez-une-application-full-stack-complete.git

   cd Developpez-une-application-full-stack-complete/front
   ```

2. Install the dependencies:

   ```sh
   npm install
   ```

## Usage

1. Run the development server:

   ```sh
   ng serve
   ```

2. Open your browser and navigate to `http://localhost:4200`.

## Project Structure

### Components

- **AppComponent**: The root component of the application. It initializes the authentication service and handles layout changes based on the route.
- **HeaderComponent**: Displays the application header with navigation links.
- **ArticleDetailsContainerComponent**: Displays the details of an article and its comments. Allows users to add comments.
- **ProfileFormComponent**: Manages the user profile form. Allows users to update their profile information and logout.
- **ThemesListComponent**: Displays a list of themes and allows users to subscribe to a theme.
- **UserThemesComponent**: Displays the user's subscribed themes and allows users to unsubscribe from a theme.
- **ThemeCardComponent**: Displays a theme card and allows users to subscribe to the theme.

### Services

- **AuthService**: Manages authentication-related operations such as login, registration, and token management.
- **ProfileService**: Manages user profile operations such as fetching and updating the profile.
- **ArticleService**: Manages article-related operations such as fetching, adding articles, and adding comments.
- **ThemeService**: Manages theme-related operations such as fetching themes, subscribing, and unsubscribing from themes.

### Models

- **User**: Represents a user in the application.
- **Article**: Represents an article with properties such as id, title, content, createdAt, authorName, themeName, and comments.
- **Comment**: Represents a comment with properties such as id, content, createdAt, and authorName.
- **Theme**: Represents a theme with properties such as id, name, and description.
- **LoginRequest**: Represents a login request with email and password.
- **RegisterRequest**: Represents a registration request with name, email, and password.
- **UpdateProfileRequest**: Represents an update profile request with name and email.

## Code Examples

### Using the AuthService

```typescript
import { AuthService } from './auth/services/auth.service';

constructor(private authService: AuthService) {}

login() {
  this.authService.login({ email: 'test@example.com', password: 'password' })
    .subscribe(response => {
      console.log('Logged in successfully', response);
    }, error => {
      console.error('Login failed', error);
    });
}
```

### Using the ThemeService

```typescript
import { ThemeService } from './services/theme.service';

constructor(private themeService: ThemeService) {}

subscribeToTheme(themeId: number) {
  this.themeService.subscribeToTheme(themeId)
    .subscribe(response => {
      console.log('Subscribed to theme successfully', response);
    }, error => {
      console.error('Failed to subscribe to theme', error);
    });
}
```

### Using PrimeNG Toast Notifications

```typescript
import { MessageService } from 'primeng/api';

constructor(private messageService: MessageService) {}

showSuccess() {
  this.messageService.add({
    severity: 'success',
    summary: 'Success',
    detail: 'Operation completed successfully'
  });
}

showError() {
  this.messageService.add({
    severity: 'error',
    summary: 'Error',
    detail: 'Operation failed'
  });
}
```

## Contributing

1. Fork the repository
2. Create a new branch (`git checkout -b feature-branch`)
3. Make your changes
4. Commit your changes (`git commit -m 'Add new feature'`)
5. Push to the branch (`git push origin feature-branch`)
6. Create a new Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgements

- [Angular](https://angular.io/)
- [PrimeNG](https://www.primefaces.org/primeng/)
- [PrimeFlex](https://www.primefaces.org/primeflex/)
- [PrimeIcons](https://www.primefaces.org/primeicons/)
- [RxJS](https://rxjs.dev/)

## Back - MDD API

MDD API is a RESTful web service designed to manage articles, comments, and user subscriptions to various themes. It provides endpoints for user authentication, managing articles and comments, subscribing to themes, and more.

## Features

- **User Authentication**: Secure user registration and login functionality with JWT token-based authentication.
- **Article Management**: Create, read, update, and delete articles with associated comments.
- **Theme Subscription**: Allow users to subscribe to themes of interest.
- **User Profile Management**: Update user profiles and retrieve subscribed themes.

## Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- Spring Security
- JWT (JSON Web Tokens)
- MySQL

## Setup

1. **Clone the repository**:

```sh
   git clone git@github.com:jbpoujol/Developpez-une-application-full-stack-complete.git
```

2. **Set up the database**:

   - Create a MySQL database named `mdd`.
   - Update the database configurations in `application.properties` file.

3. **Run the application**:

```sh
   ./mvnw spring-boot:run
```

4. **Access the API**:

   The API will be available at `http://localhost:8080`.

## API Endpoints

- **Authentication**:

  - `POST /api/v1/auth/register`: Register a new user.
  - `POST /api/v1/auth/login`: Authenticate and generate a JWT token.

- **Articles**:

  - `GET /api/v1/articles`: Get all articles.
  - `GET /api/v1/articles/{id}`: Get an article by ID.
  - `POST /api/v1/articles`: Add a new article.
  - `POST /api/v1/articles/{id}/comments`: Add a comment to an article.

- **Themes**:

  - `GET /api/v1/themes`: Get all themes.
  - `POST /api/v1/themes/{id}/subscribe`: Subscribe to a theme.
  - `POST /api/v1/themes/{id}/unsubscribe`: Unsubscribe from a theme.

- **User Profile**:
  - `GET /api/v1/profile`: Get current user details.
  - `PUT /api/v1/profile`: Update user profile.

## Contributing

Contributions are welcome! Feel free to submit bug reports, feature requests, or pull requests. Make sure to follow the existing code style and conventions.
