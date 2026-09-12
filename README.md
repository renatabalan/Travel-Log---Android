# TravelLog Android App

TravelLog is an Android application built with Kotlin and Jetpack Compose that allows users to explore travel destinations and create personal travel logs with notes and ratings.

The app was developed as part of a university Android development assignment and demonstrates modern Android UI development, navigation, state management and interactive CRUD-style functionality.

## Features

- Browse a list of travel destinations
- View destination details
- Add personal travel notes
- Rate destinations from 1–10
- View previously added travel logs
- Search travel logs by destination or note
- Quickly add a new log from the logs screen
- Edit existing travel notes
- Delete travel logs with confirmation
- Navigate between destinations and logs using a bottom navigation bar
- Snackbar feedback when a new log is saved
- Animated display of travel-log content

## Tech Stack

- Kotlin
- Android SDK
- Android Studio
- Jetpack Compose
- Material 3
- Navigation Compose
- ViewModel
- Compose State
- `rememberSaveable`
- LazyColumn
- Android Material Icons

## Architecture

The application uses a shared `TravelViewModel` to manage destinations, travel logs and search state across multiple Compose screens.

Main application flow:

```text
Destinations
    ↓
Destination Details
    ↓
Add Note + Rating
    ↓
Travel Logs
    ↓
Search / Edit / Delete
