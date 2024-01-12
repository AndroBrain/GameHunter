import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import app.App
import app.ui.screen.root.DefaultRootComponent
import app.ui.screen.root.RootComponent
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry

fun MainViewController() = ComposeUIViewController {
    val root: RootComponent = remember {
        DefaultRootComponent(
            context = DefaultComponentContext(LifecycleRegistry()),
        )
    }
    App(root)
}
