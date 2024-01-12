package app.util

import java.awt.Desktop
import java.net.URI

class DesktopBrowserOpener : BrowserOpener {
    override fun openLink(url: String) {
        Desktop.getDesktop().browse(URI.create(url))
    }
}
