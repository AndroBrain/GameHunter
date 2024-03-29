package app.ui.screen.root

import app.di.FrameworkModule
import app.di.SharedModule
import app.ui.screen.alert.DefaultAlertComponent
import app.ui.screen.home.DefaultHomeComponent
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value

interface RootComponent {
    val childStack: Value<ChildStack<*, Child>>
}

class DefaultRootComponent(
    context: ComponentContext,
    private val sharedModule: SharedModule,
    private val frameworkModule: FrameworkModule,
) : RootComponent, ComponentContext by context {
    private val navigation = StackNavigation<ScreenConfig>()

    override val childStack: Value<ChildStack<*, Child>> = childStack(
        source = navigation,
        serializer = ScreenConfig.serializer(),
        initialConfiguration = ScreenConfig.Home,
        handleBackButton = true,
        childFactory = ::createChild,
    )

    private fun createChild(
        config: ScreenConfig,
        context: ComponentContext,
    ): Child = when (config) {
        ScreenConfig.Home -> Child.ScreenHome(
            DefaultHomeComponent(
                context = context,
                navigateToAlerts = { navigation.push(ScreenConfig.Alert) },
                getDealsUseCase = sharedModule.provideGetGamesUseCase(),
                getShopsUseCase = sharedModule.provideGetShopsUseCase(),
                getGameWithDealsUseCase = sharedModule.provideGetGameWithDealsUseCase(),
                setAlertUseCase = sharedModule.provideSetAlertUseCase(),
                getAlertEmailUseCase = sharedModule.provideGetAlertEmailUseCase(),
                browserOpener = frameworkModule.provideBrowserOpener(),
            )
        )

        ScreenConfig.Alert -> Child.ScreenAlert(
            DefaultAlertComponent(
                context = context,
                getAlertsUseCase = sharedModule.provideGetAlertsUseCase(),
                deleteAlertUseCase = sharedModule.provideDeleteAlertUseCase(),
                close = { navigation.pop() },
            )
        )
    }
}
