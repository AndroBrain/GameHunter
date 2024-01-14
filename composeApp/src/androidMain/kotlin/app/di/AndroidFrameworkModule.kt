package app.di

import android.content.Context
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import app.util.AndroidBrowserOpener
import app.util.BrowserOpener
import com.androbrain.gamehunter.GameHunterDatabase

class AndroidFrameworkModule(
    private val context: Context,
) : FrameworkModule {
    private val db by lazy {
        GameHunterDatabase(
            driver = AndroidSqliteDriver(GameHunterDatabase.Schema, context, "GAME_HUNTER_DB")
        )
    }

    override fun provideBrowserOpener(): BrowserOpener = AndroidBrowserOpener(context = context)
    override fun provideDatabase() = db
}
