package app.di

import app.util.BrowserOpener

interface FrameworkModule {
    fun provideBrowserOpener(): BrowserOpener
}
