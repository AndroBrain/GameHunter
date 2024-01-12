package com.androbrain.gamehunter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import app.App
import app.ui.screen.root.DefaultRootComponent
import app.ui.screen.root.RootComponent
import com.arkivanov.decompose.retainedComponent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root: RootComponent = retainedComponent {
            DefaultRootComponent(it)
        }

        setContent { App(root = root) }
    }
}
