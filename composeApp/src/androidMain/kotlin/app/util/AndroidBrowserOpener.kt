package app.util

import android.content.Context
import android.content.Intent
import android.net.Uri

class AndroidBrowserOpener(
    private val context: Context,
) : BrowserOpener {
    override fun openLink(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }
}
