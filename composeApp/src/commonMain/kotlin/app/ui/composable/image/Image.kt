package app.ui.composable.image

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import app.ui.theme.Resources
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
fun AutoSizedByHeightAsyncImage(
    modifier: Modifier = Modifier,
    url: String,
    targetWidth: Dp,
    targetHeight: Dp,
    contentDescription: String? = null,
    aspectRatio: Float = 16f / 9,
    shape: Shape = RectangleShape,
    contentScale: ContentScale = ContentScale.Crop,
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
                val imageSize = if (size.height > size.width) {
                    DpSize(width = targetWidth, height = targetHeight)
                } else {
                    DpSize(
                        width = targetHeight * aspectRatio,
                        height = targetHeight,
                    )
                }
                Image(
                    modifier = Modifier
                        .size(imageSize)
                        .clip(shape),
                    painter = painter,
                    contentDescription = contentDescription,
                    contentScale = contentScale,
                )
            }

            is Resource.Failure -> {
                Box(modifier = Modifier.size(width = targetWidth, height = targetHeight)) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = Resources.strings.errImage,
                        color = MaterialTheme.colorScheme.error,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}
