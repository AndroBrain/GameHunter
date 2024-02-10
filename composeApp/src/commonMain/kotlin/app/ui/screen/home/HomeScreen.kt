package app.ui.screen.home

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import app.ui.composable.NoItemsContent
import app.ui.composable.modifier.scaleOnClick
import app.ui.dialog.game.GameDialog
import app.ui.screen.home.deal.DealCard
import app.ui.screen.home.params.DealParams
import app.ui.screen.home.recently.viewed.RecentlyViewedContent
import app.ui.theme.Resources
import app.util.composable.modifier.ignoreHorizontalParentPadding
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState

private const val ITEMS_TO_SHOW_MORE_DEALS = 5

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    component: HomeComponent,
) {
    val gridState = rememberLazyGridState()
    val state by component.state.collectAsState()
    val showMore by remember {
        derivedStateOf {
            val visibleItemsInfo = gridState.layoutInfo.visibleItemsInfo
            val lastVisibleItem = visibleItemsInfo.lastOrNull()
            lastVisibleItem != null && lastVisibleItem.index + ITEMS_TO_SHOW_MORE_DEALS > gridState.layoutInfo.totalItemsCount
        }
    }
    LaunchedEffect(showMore) {
        if (showMore) {
            component.getMoreDeals()
        }
    }
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier.fillMaxWidth().padding(Resources.dimens.screenSpacingSmall),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                val keyboard = LocalSoftwareKeyboardController.current
                OutlinedTextField(
                    modifier = Modifier.weight(1F),
                    value = state.query,
                    onValueChange = component::changeQuery,
                    trailingIcon = {
                        Icon(imageVector = Icons.Default.Search, contentDescription = null)
                    },
                    shape = CircleShape,
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            keyboard?.hide()
                            component.getInitialDeals()
                        },
                    ),
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Sentences,
                        imeAction = ImeAction.Search,
                    ),
                    singleLine = true,
                    placeholder = { Text(text = Resources.strings.gameNamePlaceholder) }
                )
                Spacer(modifier = Modifier.width(Resources.dimens.viewsSpacingSmall))
                val alertInteractionSource = remember { MutableInteractionSource() }
                IconButton(
                    modifier = Modifier.scaleOnClick(alertInteractionSource),
                    interactionSource = alertInteractionSource,
                    onClick = component::openAlerts,
                ) {
                    Icon(imageVector = Icons.Default.Notifications, contentDescription = null)
                }
            }
        },
        modifier = modifier,
    ) { insets ->
        if (state.isInError) {
            Column(
                modifier = Modifier.padding(insets).fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = Resources.strings.loadingError,
                    style = MaterialTheme.typography.titleMedium,
                )
                Spacer(modifier = Modifier.height(Resources.dimens.viewsSpacingSmall))
                Button(onClick = component::getInitialDeals) {
                    Text(Resources.strings.reload)
                }
            }
            return@Scaffold
        }
        Column(modifier = Modifier.padding(insets).fillMaxSize()) {
            DealParams(
                modifier = Modifier.padding(vertical = Resources.dimens.viewsSpacingSmall),
                state = state,
                component = component,
            )
            Crossfade(
                modifier = Modifier.weight(1F),
                targetState = state.isLoadingInitial,
            ) { isLoadingInitial ->
                Column(modifier = Modifier.fillMaxSize()) {
                    if (isLoadingInitial) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                        }
                    } else {
                        if (state.deals.isEmpty()) {
                            NoItemsContent(
                                modifier = Modifier.fillMaxSize(),
                                imageVector = Icons.Default.ShoppingCart,
                                text = Resources.strings.noGamesUnderFilter,
                            )
                        } else {
                            val dealModifier =
                                Modifier.padding(bottom = Resources.dimens.viewsSpacingSmall)
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
                                if (state.recentlyViewed.isNotEmpty()) {
                                    item(span = { GridItemSpan(maxLineSpan) }) {
                                        RecentlyViewedContent(
                                            modifier = Modifier.fillMaxWidth()
                                                .ignoreHorizontalParentPadding(horizontal = Resources.dimens.screenSpacingSmall),
                                            items = state.recentlyViewed,
                                            onClick = { model -> component.openRecentlyViewed(model.cheapSharkGameID) }
                                        )
                                    }
                                }
                                items(state.deals) { deal ->
                                    DealCard(
                                        modifier = dealModifier,
                                        deal = deal,
                                        onClick = { component.openGame(deal) },
                                    )
                                }
                                if (state.isLoadingMore) {
                                    item(span = { GridItemSpan(maxLineSpan) }) {
                                        Box(modifier = Modifier.fillMaxSize()) {
                                            CircularProgressIndicator(
                                                modifier = Modifier.align(
                                                    Alignment.TopCenter
                                                )
                                            )
                                        }
                                    }
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
