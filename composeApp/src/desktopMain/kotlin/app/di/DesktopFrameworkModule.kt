package app.di

import app.util.BrowserOpener
import app.util.DesktopBrowserOpener

class DesktopFrameworkModule : FrameworkModule {
    override fun provideBrowserOpener(): BrowserOpener = DesktopBrowserOpener()
}
