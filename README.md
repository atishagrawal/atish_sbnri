# Atish SBNRI

Atish SBNRI is a test app, assigned as a task to Atish Agrawal from the SBNRI Tech Team to evaluate my coding. The app must fetch data from Github API and display the list in a RecyclerView.

## Tasks:
  - Use RecyclerView to show the items.
  - Use MVVM pattern for the architecture and handle screen orientation(landscape/portrait)
  - Add pagination and allow the user to scroll down to load more results
  - Store the data in Realm/ Room so that users can see the existing fetched data even if the internet is not available
  - Add open_issues_count, license, permissions, name and description field for each cell in the recycler view

# Implementation

### MVVM
  - MVVM is one of the architectural patterns which enhances separation of concerns, and it allows separating the user interface logic from the business logic. Its target is to achieve Keeping UI code free and straightforward of app logic to make it easier to manage.

### Retrofit
  - Retrofit is a type-safe REST client for Android, Java and Kotlin developed by Square. The library provides a powerful framework for authenticating and interacting with APIs and sending network requests with OkHttp.


### LiveData
  - A part of Android Jetpack
  - LiveData is an observable data holder class. Unlike a regular observable, LiveData is lifecycle-aware, meaning it respects the lifecycle of other app components, such as activities, fragments, or services. This awareness ensures LiveData only updates app component observers that are in an active lifecycle state.

### Room Library (Database)
  - Room provides an abstraction layer over SQLite to allow fluent database access while harnessing the full power of SQLite.

### Downloads

| File | Link |
| ------ | ------ |
| APK | [atish_sbnri.apk][APKDownload] |



License
----
MIT



   [APKDownload]: <https://github.com/atishagrawal/atish_sbnri/blob/master/APK/atish_sbnri.apk?raw=true>
