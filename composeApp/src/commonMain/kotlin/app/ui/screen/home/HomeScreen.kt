package app.ui.screen.home

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import app.ui.dialog.game.GameDialog
import app.ui.screen.home.deal.DealCard
import app.ui.screen.home.params.DealParams
import app.ui.theme.Resources
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState

private const val ITEMS_TO_SHOW_MORE_DEALS = 5

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    component: HomeComponent,
) {
    val gridState = rememberLazyGridState()
    val state by component.state.collectAsState()
    LaunchedEffect(Unit) {
        component.getInitialDeals()
    }
    val visibleItemsInfo = gridState.layoutInfo.visibleItemsInfo
    LaunchedEffect(visibleItemsInfo) {
        val lastVisibleItem = visibleItemsInfo.lastOrNull() ?: return@LaunchedEffect
        if (lastVisibleItem.index + ITEMS_TO_SHOW_MORE_DEALS > gridState.layoutInfo.totalItemsCount) {
            component.getMoreDeals()
        }
    }
    Scaffold(
        topBar = {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth().padding(Resources.dimens.screenSpacingSmall),
                value = state.query,
                onValueChange = component::changeQuery,
                trailingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null)
                },
                shape = CircleShape,
                keyboardActions = KeyboardActions(
                    onSearch = { component.getInitialDeals() },
                ),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences,
                    imeAction = ImeAction.Search,
                ),
                singleLine = true,
                placeholder = { Text(text = Resources.strings.gameNamePlaceholder) }
            )
        },
        modifier = modifier,
    ) { insets ->
        val dealModifier = Modifier.padding(bottom = Resources.dimens.viewsSpacingSmall)
        Crossfade(
            modifier = Modifier.padding(insets).fillMaxSize(),
            targetState = state.isLoadingInitial,
        ) { isLoadingInitial ->
            Column(modifier = Modifier.fillMaxSize()) {
                DealParams(
                    modifier = Modifier.padding(vertical = Resources.dimens.viewsSpacingSmall),
                    state = state,
                    component = component,
                )
                if (isLoadingInitial) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                } else {
                    LazyVerticalGrid(
                        state = gridState,
                        modifier = Modifier.fillMaxSize(),
                        columns = GridCells.Adaptive(minSize = Resources.dimens.dealMinSize),
                        contentPadding = PaddingValues(
                            start = Resources.dimens.screenSpacingSmall,
                            top = Resources.dimens.screenSpacingSmall,
                            end = Resources.dimens.screenSpacingSmall,
                            bottom = Resources.dimens.screenSpacingMedium,
                        ),
                        horizontalArrangement = Arrangement.spacedBy(Resources.dimens.viewsSpacingSmall)
                    ) {
                        items(state.deals) { deal ->
                            DealCard(
                                modifier = dealModifier,
                                deal = deal,
                                onClick = {
                                    component.openGame(deal.gameID)
                                },
                            )
                        }
                        if (state.isLoadingMore) {
                            item(span = { GridItemSpan(maxLineSpan) }) {
                                Box(modifier = Modifier.fillMaxSize()) {
                                    CircularProgressIndicator(modifier = Modifier.align(Alignment.TopCenter))
                                }
                            }
                        }
                    }
                }
            }
        }

        val gameSlot by component.gameSlot.subscribeAsState()
        gameSlot.child?.instance?.let {
            GameDialog(
                modifier = Modifier.height(Resources.dimens.dialogHeight)
                    .width(Resources.dimens.dialogWidth),
                component = it,
            )
        }
    }
}
