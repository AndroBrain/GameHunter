package app.ui.screen.root

import app.ui.screen.alert.AlertComponent
import app.ui.screen.home.HomeComponent

sealed class Child {
    data class ScreenHome(val component: HomeComponent) : Child()
    data class ScreenAlert(val component: AlertComponent) : Child()
}
