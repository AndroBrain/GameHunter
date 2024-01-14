package com.androbrain.gamehunter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import app.App
import app.di.AndroidFrameworkModule
import app.di.DefaultSharedModule
import app.ui.screen.root.DefaultRootComponent
import app.ui.screen.root.RootComponent
import com.arkivanov.decompose.retainedComponent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root: RootComponent = retainedComponent { componentContext ->
            val frameworkModule = AndroidFrameworkModule(context = applicationContext)
            DefaultRootComponent(
                context = componentContext,
                sharedModule = DefaultSharedModule(frameworkModule.provideDatabase()),
                frameworkModule = frameworkModule,
            )
        }

        setContent { App(root = root) }
    }
}
