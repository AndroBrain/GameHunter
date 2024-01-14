package app.ui.screen.alert

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import app.ui.theme.Resources

@Composable
fun AlertScreen(
    modifier: Modifier = Modifier,
    component: AlertComponent,
) {
    val state by component.state.collectAsState()
    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = Resources.strings.alerts)
                },
                navigationIcon = {
                    IconButton(onClick = component::onClose) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { insets ->
        LazyColumn(
            modifier = Modifier.padding(insets),
            contentPadding = PaddingValues(
                start = Resources.dimens.screenSpacingSmall,
                end = Resources.dimens.screenSpacingSmall,
                top = Resources.dimens.viewsSpacingSmall,
                bottom = Resources.dimens.screenSpacingMedium,
            ),
            verticalArrangement = Arrangement.spacedBy(Resources.dimens.viewsSpacingSmall),
        ) {
            items(state.alerts) { alert ->
                AlertCard(alert = alert, onDelete = { component.deleteAlert(alert) })
            }
        }
    }
}
