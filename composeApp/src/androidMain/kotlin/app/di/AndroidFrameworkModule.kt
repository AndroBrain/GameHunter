package app.di

import android.content.Context
import app.util.AndroidBrowserOpener
import app.util.BrowserOpener

class AndroidFrameworkModule(
    private val context: Context,
) : FrameworkModule {
    override fun provideBrowserOpener(): BrowserOpener = AndroidBrowserOpener(context = context)
}
