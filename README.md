# Little Lemon

Little lemon is the Capstone project for Meta's Android Developer Course on Coursera made in Kotlin.

## Built With

**[Kotlin:](https://kotlinlang.org/)** As the programming language.

**[ktor:](https://ktor.io/)** For fetching data asynchronously from the server.

**[Jetpack Compose:](https://developer.android.com/jetpack/compose/documentation)** To make the UI.

**[Room:](https://developer.android.com/training/data-storage/room)** To cache the network data locally.

**[Glide Compose:](https://bumptech.github.io/glide/int/compose.html)** To load images asynchronously.

**[Jetpack Navigation:](https://developer.android.com/jetpack/compose/navigation)** For navigation between screens.

## Installation

Simply clone this repository and open LittleLemon folder (android project folder) in android studio. To clone:

```
  https://github.com/sDevPrem/little-lemon.git
```

## Architecture

This app made using Android recommended [MVVM]("https://developer.android.com/topic/architecture") Architecture. Packages and their roles:

* `data` - It is the data layer which contains class realated to database.

  * `local` - local data base(Room)
  * `network` - REST Client
  * `model` - data classes
* `ui` - It is UI layer which contains composable, view-models and navigation.
