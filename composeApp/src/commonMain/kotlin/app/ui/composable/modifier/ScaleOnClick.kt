package app.ui.composable.modifier

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Indication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.scale
import androidx.compose.ui.semantics.Role

private const val SCALE_DOWN_VALUE = 0.95F

fun Modifier.scaleOnClick(
    interactionSource: MutableInteractionSource,
    target: Float = SCALE_DOWN_VALUE,
): Modifier = composed {
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(targetValue = if (isPressed) target else 1F)
    scale(scale)
}

fun Modifier.scaleClickable(
    interactionSource: MutableInteractionSource,
    target: Float = SCALE_DOWN_VALUE,
    indication: Indication?,
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    onClick: () -> Unit,
): Modifier = clickable(interactionSource, indication, enabled, onClickLabel, role, onClick)
    .scaleOnClick(interactionSource = interactionSource, target = target)
