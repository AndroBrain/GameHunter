package app.di

import app.util.BrowserOpener
import com.androbrain.gamehunter.GameHunterDatabase

interface FrameworkModule {
    fun provideBrowserOpener(): BrowserOpener
    fun provideDatabase(): GameHunterDatabase
}
