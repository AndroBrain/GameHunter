package app.ui.screen.home

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import app.ui.theme.Resources
import app.util.dealLink

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    component: HomeComponent,
) {
    LaunchedEffect(Unit) {
        component.getDeals()
    }
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
        val dealModifier = Modifier.padding(bottom = Resources.dimens.viewsSpacingSmall)
        Crossfade(
            modifier = Modifier.padding(insets).fillMaxSize(),
            targetState = state.isLoadingInitial,
        ) { isLoadingInitial ->
            if (isLoadingInitial) {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            } else {
                LazyVerticalGrid(
                    modifier = Modifier.fillMaxSize(),
                    columns = GridCells.Adaptive(minSize = Resources.dimens.dealMinSize),
                    contentPadding = PaddingValues(
                        start = Resources.dimens.screenSpacingMedium,
                        top = Resources.dimens.screenSpacingSmall,
                        end = Resources.dimens.screenSpacingMedium,
                        bottom = Resources.dimens.screenSpacingMedium,
                    ),
                    horizontalArrangement = Arrangement.spacedBy(Resources.dimens.viewsSpacingSmall)
                ) {
                    items(state.deals) { deal ->
                        DealCard(
                            modifier = dealModifier,
                            deal = deal,
                            onClick = { component.openInBrowser(dealLink(deal.dealID)) },
                        )
                    }
                }
            }
        }
    }
}
