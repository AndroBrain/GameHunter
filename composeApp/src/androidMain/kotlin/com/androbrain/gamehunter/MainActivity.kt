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
            DefaultRootComponent(
                context = componentContext,
                sharedModule = DefaultSharedModule(),
                frameworkModule = AndroidFrameworkModule(context = applicationContext),
            )
        }

        setContent { App(root = root) }
    }
}
