Assignment 1
Renata Balan
Programming Mobile Devices

This app is an Android app that lets users browse a list of travel destinations,
view details about each destination,
add personal notes and ratings,
view,search and manage saved logs

The app has multiscreen navigation, state management and lifecycle- aware components

Features Implemented:
Screen 1 - Destinations List
 - A scrollable list of 10 destinations using LazyColumn
 - Each destination shows an icon , name country and a summary

Screen 2 - Destination Details
 - Information on selected destination
 - Includes a TextField to enter travel notes
 - A bar to rate the destination
 - shared viewmodel
 - Uses rememberSaveable

Screen 3 - Logs
- All saved travel logs with title, note and icon
- A search bar to search saved logs
- Floating Action Button to add new log directly without changing screens
-  Uses AnimatedVisibility for smooth transitions

Extra Features
- Custom colour scheme
- Icons for each destination
- Animated log list

Lifecycle-Aware Components
- viewModel = holds all destinations and logs data
- rememberSaveable = preserves form input during configuration changes
