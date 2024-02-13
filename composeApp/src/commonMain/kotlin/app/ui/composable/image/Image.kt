package app.ui.composable.image

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
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

@Composable
fun AutoSizedAsyncImage(
    modifier: Modifier = Modifier,
    url: String,
    targetWidth: Dp,
    targetHeight: Dp,
    contentDescription: String? = null,
    shape: Shape = RectangleShape,
) {
    val painterResource: Resource<Painter> = asyncPainterResource(url) {
        coroutineContext = Job() + Dispatchers.IO
        requestBuilder {
            cacheControl(CacheControl.MAX_AGE)
        }
    }
    Crossfade(modifier = modifier, targetState = painterResource) { resource ->
        when (resource) {
            is Resource.Loading -> {
                Box(modifier = Modifier.size(width = targetWidth, height = targetHeight)) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        progress = resource.progress,
                    )
                }
            }

            is Resource.Success -> {
                val painter: Painter = resource.value
                val size = with(LocalDensity.current) {
                    painter.intrinsicSize.toDpSize()
                }
                val imageModifier = if (size.height > size.width) {
                    val aspectRatio = size.height / size.width
                    Modifier.size(
                        width = targetWidth,
                        height = targetWidth * aspectRatio,
                    )
                } else {
                    val aspectRatio = size.width / size.height
                    Modifier.size(
                        width = targetHeight * aspectRatio,
                        height = targetHeight,
                    )
                }
                Image(
                    modifier = imageModifier.clip(shape),
                    painter = painter,
                    contentDescription = contentDescription,
                )
            }

            is Resource.Failure -> {
                // TODO
            }
        }
    }
}
