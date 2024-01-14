package app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import app.ui.screen.alert.AlertScreen
import app.ui.screen.home.HomeScreen
import app.ui.screen.root.Child
import app.ui.screen.root.RootComponent
import app.ui.theme.AppTheme
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState

@Composable
fun App(
    root: RootComponent,
) {
    AppTheme {
        val childStack by root.childStack.subscribeAsState()
        Children(
            stack = childStack,
            animation = stackAnimation(slide())
        ) { child ->
            when (val instance = child.instance) {
                is Child.ScreenHome -> HomeScreen(component = instance.component)
                is Child.ScreenAlert -> AlertScreen(component = instance.component)
            }
        }
    }
}
