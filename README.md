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

## API
The app currently uses [Cheap Shark API](https://apidocs.cheapshark.com/#a2620d3f-683e-0396-61e7-3fe4d30ea376) to get all the game deals, thumbnails, prices etc.
