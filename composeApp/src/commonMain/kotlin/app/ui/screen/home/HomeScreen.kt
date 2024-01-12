package app.ui.screen.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import app.ui.theme.Resources

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    component: HomeComponent,
) {
    val state by component.state.collectAsState()
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = Resources.strings.appName)
                }
            )
        },
        modifier = modifier,
    ) { insets ->
        LazyColumn(modifier = Modifier.padding(insets).fillMaxSize()) {
            items(state.games) { item ->
                Text(text = item.name)
            }
        }
    }
}
