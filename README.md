<img src="https://github.com/AndroBrain/GameHunter/assets/75139757/70bb6390-74ea-4fa1-a882-c03160e2b8f5" width="320" height="320">

## Project description

I'm Michal Ankiersztajn, an Applied Computer Science Engineering Student at Warsaw University of Technology. 

I created **Game Hunter** because games are increasingly expensive nowadays. Oftentimes, we want to play games together, but when they cost more than 30$, the costs of these games are too high for people in Poland. Therefore, I decided to solve this issue!

The app allows users to browse, search, sort and filter games with great deals. If you want to buy a game that costs too much, you can set up an alert with the upper price limit. Cards present more detailed information like release date, deal rating, steam rating, steam reviews and retail price. You're able to choose a shop from which you want to buy a game.

The app is aimed at people who want to play together and have fun but don't want to spend all their savings on them. As well as for those who simply want to save some money.

Amazingly I was able to share 100% UI code with Compose multiplatform and 100% of data altering logic with SQLDelight and Ktor.

## Presentation

### Android

[android_preview.webm](https://github.com/AndroBrain/GameHunter/assets/75139757/9d9dea01-a993-4cc4-93cf-ad64e8298c7c)

### Desktop

https://github.com/AndroBrain/GameHunter/assets/75139757/8cd2b362-6107-457c-821c-1c4bb71a19c4

## How to compile and run the application?

### Desktop

The easiest way to launch the Desktop client is to start it from the terminal in the root directory:

`./gradlew composeApp:run`

Or type it in **Run Anything** in IntelliJ/Android Studio Window.

### Android

The easiest way to launch the Android client is to run from Android Studio using the generated build configuration `composeApp`. It'll build and deploy it on a selected physical device or emulator. I would strongly suggest using this method because it's the easiest one.

## Project structure

* `gradle` - containts `libs.versions.toml` file with all the project, libraries and plugins configurations.
* `composeApp` - module containing the app build in compose.
* `androidMain` - module containing Android specific logic.
* `desktopMain` - module containing Desktop specific logic.
* `iosMain` - module containing ios specific logic (the app currently doesn't support iOS).
* `commonMain`
    * `kotlin` - module with shared kotlin code
      * `app` - directory with application layer (Compose UI + Presentation).
      * `data` - directory with data layer (Local storage with SQLDelight and Networking with Ktor)
      * `domain` - directory with domain layer. Independent from other layers.
    * `resources` - module with resources used by the app.
    * `sqldelight` - directory that contains all the SQL statements required to make SQLDelight work correctly.


## Tech Stack

| What                                    | How                                                                                                                                                                             |
|-----------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 🖥️ User Interface (Android, Desktop)    | [Compose Multiplatform](https://www.jetbrains.com/lp/compose-multiplatform/)                                                                                                          |
| 🧩 Architecture                         | MVVM + Clean Architecture                                                                                                        |
| 💉 DI                                   | Manual (SharedModule + FrameworkModule)                                                                                                                                                 |
| 🔃 Async                                | [Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) + [Flow](https://kotlinlang.org/docs/flow.html) | |
| 🗃️ Data Storage                         | [SqlDelight](https://github.com/cashapp/sqldelight) 
| 🌐 Networking                           | [Ktor](https://ktor.io/docs/getting-started-ktor-client.html)
| 🖼️ Image loading                        | [Kamel](https://github.com/Kamel-Media/Kamel)
| 🧭 Navigation & Shared ViewModels       | [Decompose](https://github.com/arkivanov/Decompose)
| 🎨 Design system                        | [Material Design 3](https://m3.material.io/)

## What is shared and what is not?
| What                                    | What is shared?                                                                                                                                                                            |
|-----------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Domain layer                            | Everything
| Data layer                              | Everything
| Presentation layer                      | Almost everything, opening link in the browser is not shared

## Tools used to create the project

* Android Studio Hedgehog | 2023.1.1
* [Material Theme Builder](https://material-foundation.github.io/material-theme-builder)
* [KMP Wizard](https://kmp.jetbrains.com/)
* App Icon created using [Figma](https://www.figma.com/) with resources from [svgrepo.com](svgrepo.com) and [Google Fonts](https://fonts.google.com/icons)

## API

The app currently uses [Cheap Shark API](https://apidocs.cheapshark.com/#a2620d3f-683e-0396-61e7-3fe4d30ea376) to get all the game deals, thumbnails, prices etc.

## 
