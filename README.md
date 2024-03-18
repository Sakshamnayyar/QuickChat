# QuickChat

QuickChat is a simple Android application that allows users to open any WhatsApp chat without the need to save the phone number in their contacts. 
This project serves as a demonstration of MVVM architecture, coroutines, Room Database, and HILT for dependency injection.

## Features

- **Open WhatsApp Chat**: Users can enter a phone number and open the corresponding WhatsApp chat directly from the application without saving the number.

## Technologies Used

- **MVVM Architecture**: The project follows the Model-View-ViewModel architectural pattern to separate the presentation layer from the business logic.
- **Coroutines**: Kotlin Coroutines are used for asynchronous programming, providing easy-to-use concurrency.
- **Room Database**: Room Persistence Library is used to store and manage user data locally in an SQLite database.
- **HILT (Dagger-Hilt)**: HILT is used for dependency injection to provide dependencies to the different parts of the application.
- **Android Jetpack Components**: Utilizes various Jetpack components such as LiveData, ViewModel, Navigation, etc., to build robust and maintainable Android apps.

## Usage

1. Launch the application on your device.
2. Enter the phone number you want to open a WhatsApp chat with.
3. Tap on the "Open Chat" button.
4. The application will open the corresponding WhatsApp chat.
