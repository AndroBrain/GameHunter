import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import app.App
import app.di.DefaultSharedModule
import app.di.DesktopFrameworkModule
import app.ui.screen.root.DefaultRootComponent
import app.ui.screen.root.RootComponent
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.extensions.compose.jetbrains.lifecycle.LifecycleController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
fun main() {
    val lifecycle = LifecycleRegistry()

    val root: RootComponent = runOnUiThread {
        DefaultRootComponent(
            context = DefaultComponentContext(lifecycle = lifecycle),
            sharedModule = DefaultSharedModule(),
            frameworkModule = DesktopFrameworkModule(),
        )
    }
    application {
        val windowState = rememberWindowState()

        LifecycleController(lifecycle, windowState)

        Window(
            onCloseRequest = ::exitApplication,
            title = "GameHunter",
            icon = painterResource("app-icon.xml"),
        ) {
            App(root = root)
        }
    }
}
