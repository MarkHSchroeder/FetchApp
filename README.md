# FetchApp

## Overview
Retrieve a list of items from https://fetch-hiring.s3.amazonaws.com/hiring.json. The items are grouped by listId, then sorted to display in order. Any names that are blank or null are discarded.

## Features
- Displays a list of items grouped by listId and arranged in order
    
## Requirements
- **Android Version**: Android 7.0 (Nougat) or higher

## Setup and Installation

1. **Clone the Repository:**
   Clone the repository to your local machine using Git:

   ```bash
   git clone https://github.com/MarkHSchroeder/FetchApp.git
   
2. **Open the Project:** Open the project in Android Studio

3. **Gradle Sync:** Sync your Gradle files to download dependencies and prepare the app for building.

4. **Build the App:** Click on Build > Make Project to compile the app. Once the build process is complete, you can run the app on an emulator or physical device.

## Usage

1. **Launch the App:** Open the application after installation.

2. **View Results:** The network call is automatically made on startup. A loading spinner will appear until the network call is complete. Once complete, the list is displayed in order.

3. **Error Handling:** If the network call fails or fails to retrieve a list, the app will show an error message.

## Technologies Used

  - **Android SDK:** For building the Android application.
  - **Jetpack Compose:** For UI.
  - **Coroutines:** For asynchronous tasks.
  - **Hilt:** For dependancy injection.
  - **Retrofit:** For network requests to fetch weather data.

## Support

For any issues, bugs, questions, or suggestions, feel free to open an issue in the repository, and the I will address it as soon as possible.

Thank you for reviewing the Fetch App!
