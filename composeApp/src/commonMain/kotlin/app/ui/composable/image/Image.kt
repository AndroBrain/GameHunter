package app.ui.composable.image

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job

@Composable
fun AsyncImage(
    modifier: Modifier = Modifier,
    url: String,
    contentDescription: String? = null,
) {
    KamelImage(
        modifier = modifier,
        resource = asyncPainterResource(url) { coroutineContext = Job() + Dispatchers.IO },
        contentDescription = contentDescription,
        onLoading = { CircularProgressIndicator(modifier = Modifier.align(Alignment.Center)) },
        contentScale = ContentScale.Crop,
    )
}
