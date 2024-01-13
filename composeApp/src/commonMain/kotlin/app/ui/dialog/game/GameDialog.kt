package app.ui.dialog.game

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import app.ui.theme.Resources

@Composable
fun GameDialog(
    modifier: Modifier = Modifier,
    component: GameComponent,
    shape: Shape = AlertDialogDefaults.shape,
    color: Color = AlertDialogDefaults.containerColor,
    tonalElevation: Dp = AlertDialogDefaults.TonalElevation,
) {
    val state by component.state.collectAsState()
    AlertDialog(
        onDismissRequest = component::onDismiss,
    ) {
        Surface(
            modifier = modifier,
            shape = shape,
            color = color,
            tonalElevation = tonalElevation,
        ) {
            Crossfade(
                targetState = state.gameWithDeals,
            ) { gameWithDeals ->
                if (gameWithDeals == null) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                } else {
                    GameDialogContent(
                        modifier = Modifier.fillMaxSize()
                            .padding(Resources.dimens.viewsSpacingMedium),
                        gameWithDeals = gameWithDeals,
                        onDealClick = component::openInStore,
                    )
                }
            }
        }
    }
}
