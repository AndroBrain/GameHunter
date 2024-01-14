package app.di

import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import app.util.BrowserOpener
import app.util.DesktopBrowserOpener
import com.androbrain.gamehunter.GameHunterDatabase

class DesktopFrameworkModule : FrameworkModule {

    private val db by lazy {
        GameHunterDatabase(
            driver = JdbcSqliteDriver("jdbc:sqlite:gamehunter.db").also { driver ->
                GameHunterDatabase.Schema.create(driver)
            }
        )
    }

    override fun provideBrowserOpener(): BrowserOpener = DesktopBrowserOpener()
    override fun provideDatabase(): GameHunterDatabase = db
}
