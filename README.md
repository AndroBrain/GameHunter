## Tech Stack

| What                                    | How                                                                                                                                                                             |
|-----------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 🖥️ User Interface (Android, Desktop)    | Jetpack Compose                                                                                                          |
| 🧩 Architecture                         | MVVM + Clean Architecture                                                                                                        |
| 💉 DI                                   | Manual (SharedModule + FrameworkModule)                                                                                                                                                 |
| 🔃 Async                                | Coroutines + Flow | |
| 🗃️ Data Storage                         | SqlDelight 
| 🌐 Networking                           | Ktor Client
| 🖼️ Image loading                        | Kamel
| 🧭 Navigation & Shared ViewModels       | Decompose
| 🎨 Design system                        | Material Design 3

## What is shared and what is not?
| What                                    | What is shared?                                                                                                                                                                            |
|-----------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Domain layer                            | Everything
| Data layer                              | Everything
| Presentation layer                      | Almost everything, opening link in the browser is not shared

## API
The app currently uses [Cheap Shark API](https://apidocs.cheapshark.com/#a2620d3f-683e-0396-61e7-3fe4d30ea376) to get all the game deals, thumbnails, prices etc.
