# Bookstore Android App

A modern, multi-module Android application built with Kotlin, Jetpack Compose, and Clean Architecture. This app allows users to browse a book catalog, view details, manage a favorites list, and maintain a shopping cart.

## 🚀 Features

- **Book Catalog**: Browse a list of available books fetched from a remote API.
- **Book Details**: Detailed information for each book, including summaries and pricing.
- **Favorites**: Mark books as favorites and view them in a dedicated tab.
- **Shopping Cart**: Add books to a cart, manage quantities, and see the total price in real-time.
- **Multi-language Support**: Fully localized in English and Spanish.

## 🏗️ Architecture

The project follows **Clean Architecture** principles and is structured into a **Multi-Module** system to promote scalability, maintainability, and loose coupling.

### Module Structure

- **`:app`**: The main entry point. It orchestrates navigation between features and initializes dependency injection.
- **`:core:common`**: Contains shared business logic, base classes, and **contracts** (interfaces) used for cross-feature communication.
- **`:core:network`**: Centralized networking logic using Retrofit and Kotlinx Serialization, including global error handling.
- **`:core:designsystem`**: The single source of truth for the app's UI. Contains the theme, typography, and reusable components.
- **`:features:books`**: catalog and book detail functionality.
- **`:features:favorites`**: management of the user's favorite books.
- **`:features:cart`**: shopping cart management.

### Key Technologies

- **UI**: Jetpack Compose with Material 3.
- **Navigation**: Compose Navigation with Type-Safe routes.
- **Dependency Injection**: Koin (using DSL and `viewModelOf`).
- **Local Database**: Room for persisting Favorites and Cart items.
- **Networking**: Retrofit 3 and OkHttp.
- **Concurrency**: Kotlin Coroutines and Flow.
- **Testing**: JUnit 4, MockK for mocking, and Turbine for testing Flows.

## 🛠️ How to Run

### Prerequisites
- Android Studio Ladybug (or newer).
- JDK 17 or higher.
- Android SDK 35+ (Target SDK is 36).

### Steps
1.  **Clone the repository**:
    ```bash
    git clone https://github.com/your-username/bookstore-android.git
    ```
2.  **Open in Android Studio**: Open the root folder of the project.
3.  **Sync Gradle**: Wait for the project to sync and download all dependencies.
4.  **Run the app**: Select the `app` configuration and click the **Run** button (or press `Shift + F10`).

## 🧪 Running Tests

Each module contains its own unit and instrumentation tests.

- **Run all unit tests**:
  ```bash
  ./gradlew test
  ```
- **Run tests for a specific module** (e.g., Cart):
  ```bash
  ./gradlew :features:cart:testDebugUnitTest
  ```
- **Run UI tests**:
  Ensure you have an emulator or device connected and run:
  ```bash
  ./gradlew connectedDebugAndroidTest
  ```

## 🌍 Localization

The app currently supports:
- 🇺🇸 English (Default)
- 🇪🇸 Spanish (Español)
