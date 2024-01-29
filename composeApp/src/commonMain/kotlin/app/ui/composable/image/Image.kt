package app.ui.composable.image

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import io.kamel.core.Resource
import io.kamel.core.utils.cacheControl
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import io.ktor.client.utils.CacheControl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job

@Composable
fun AsyncImage(
    modifier: Modifier = Modifier,
    url: String,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Crop,
) {
    val resource: Resource<Painter> = asyncPainterResource(url) {
        coroutineContext = Job() + Dispatchers.IO
        requestBuilder {
            cacheControl(CacheControl.MAX_AGE)
        }
    }
    KamelImage(
        modifier = modifier,
        resource = resource,
        contentDescription = contentDescription,
        onLoading = { CircularProgressIndicator(modifier = Modifier.align(Alignment.Center)) },
        contentScale = contentScale,
    )
}
