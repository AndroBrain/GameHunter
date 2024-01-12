package app.ui.screen.home

data class HomeState(
    val games: List<GameDisplayable> = listOf(
        GameDisplayable(name = "Test1"),
        GameDisplayable(name = "Test2"),
        GameDisplayable(name = "Test3"),
        GameDisplayable(name = "Test4"),
    ),
)
