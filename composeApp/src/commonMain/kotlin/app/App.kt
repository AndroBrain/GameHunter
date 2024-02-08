package app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import app.ui.screen.alert.AlertScreen
import app.ui.screen.home.HomeScreen
import app.ui.screen.root.Child
import app.ui.screen.root.RootComponent
import app.ui.theme.AppTheme
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import kotlinx.coroutines.launch

@Composable
fun App(
    root: RootComponent,
    darkTheme: Boolean = true,
) {
    AppTheme(darkTheme = darkTheme) {
        val snackbarHostState = remember { SnackbarHostState() }
        val coroutineScope = rememberCoroutineScope()
        val state by root.state.collectAsState()
        val message = state.message?.asString()
        LaunchedEffect(message) {
            if (message != null) {
                root.messageShown()
                coroutineScope.launch { snackbarHostState.showSnackbar(message) }
            }
        }
        Box(modifier = Modifier.fillMaxSize()) {
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
            SnackbarHost(
                modifier = Modifier.align(Alignment.BottomCenter),
                hostState = snackbarHostState,
            )
        }
    }
}
