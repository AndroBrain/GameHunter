package app.ui.dialog.notification

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import app.ui.composable.modifier.scaleOnClick
import app.ui.theme.Resources

@Composable
fun NotificationDialog(
    modifier: Modifier = Modifier,
    component: NotificationComponent,
) {
    val state by component.state.collectAsState()
    LaunchedEffect(state.dismiss) {
        if (state.dismiss) {
            component.onDismiss()
        }
    }
    AlertDialog(
        modifier = modifier,
        onDismissRequest = component::onDismiss,
        title = {
            Text(text = state.gameTitle)
        },
        text = {
            Column(modifier = Modifier.widthIn(min = TextFieldDefaults.MinWidth)) {
                if (state.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                } else {
                    OutlinedTextField(
                        value = state.email,
                        onValueChange = component::changeEmail,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                        ),
                        label = { Text(text = Resources.strings.notificationEmail) },
                        supportingText = {
                            if (!state.isEmailValid) {
                                Text(text = Resources.strings.invalidEmail)
                            }
                        },
                        isError = !state.isEmailValid,
                        maxLines = 1,
                    )
                    Spacer(modifier = Modifier.height(Resources.dimens.viewsSpacingSmall))
                    OutlinedTextField(
                        value = "${state.price}${Resources.strings.currencySign}",
                        onValueChange = component::changePrice,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Decimal,
                        ),
                        label = { Text(text = Resources.strings.notificationMaxPrice) },
                        maxLines = 1,
                    )
                }
            }
        },
        confirmButton = {
            if (!state.isLoading) {
                val interactionSource = remember { MutableInteractionSource() }
                Button(
                    modifier = Modifier.scaleOnClick(interactionSource),
                    interactionSource = interactionSource,
                    enabled = state.isEmailValid,
                    onClick = component::setAlert,
                ) {
                    Text(text = Resources.strings.addNotification)
                }
            }
        },
    )
}
