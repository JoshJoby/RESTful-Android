# RESTful Android App
This Android application is designed to communicate with a SQLite database hosted on Microsoft Azure Function Apps. The app allows users to interact with the database, perform CRUD (Create, Read, Update, Delete) operations, and retrieve data from the serverless backend.

# Installation
To install and run the Android app, follow the steps below:

Clone the repository:

    git clone https://github.com/yourusername/android-app.git
Open the project in Android Studio.

# Configure Azure Function App settings:

Retrieve the endpoint URL and authentication details of your Azure Function App hosting the SQLite database.
Update the app's configuration file or constants with the appropriate values to connect to the Azure Function App.
Build the app in Android Studio.

Connect an Android device or emulator to your development environment.

Install and run the app on the device/emulator using Android Studio.

# Usage
Once the app is installed and running on the device/emulator, you can use it to communicate with the SQLite database hosted on the Azure Function App. The app provides a user-friendly interface to perform various operations, such as:

Create new records in the database.
Retrieve data from the database.
Update existing records in the database.
Delete records from the database.
Ensure a stable internet connection is available on the device/emulator to establish communication with the Azure Function App.

# Dependencies
The Android app relies on the following dependencies:

SQLiteOpenHelper: A helper class to manage database creation and version management.
Volley: A networking library for making HTTP requests and handling responses.
Gson: A library for JSON serialization/deserialization.
Other Android framework libraries and support libraries as required.
Make sure to include these dependencies in your Android project's build.gradle file.
