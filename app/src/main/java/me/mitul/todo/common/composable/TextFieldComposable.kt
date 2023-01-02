package me.mitul.todo.common.composable

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.mitul.todo.R.drawable as AppIcon
import me.mitul.todo.R.string as AppText
import me.mitul.todo.R.string.app_name as AppName

@Composable
fun BasicField(
    @StringRes text: Int,
    value: String,
    modifier: Modifier = Modifier,
    onNewValue: (String) -> Unit,
) = OutlinedTextField(
    singleLine = true,
    modifier = modifier,
    value = value,
    onValueChange = {
        onNewValue(it)
    },
    placeholder = {
        Text(text = stringResource(id = text))
    },
)

@Composable
fun EmailField(
    value: String,
    modifier: Modifier = Modifier,
    onNewValue: (String) -> Unit,
) = OutlinedTextField(
    singleLine = true,
    modifier = modifier,
    value = value,
    onValueChange = {
        onNewValue(it)
    },
    placeholder = {
        Text(text = stringResource(id = AppText.email))
    },
    leadingIcon = {
        Icon(
            imageVector = Icons.Default.Email,
            contentDescription = "Email",
        )
    }
)

@Composable
fun PasswordField(
    value: String,
    modifier: Modifier = Modifier,
    onNewValue: (String) -> Unit,
) = PasswordField(
    value = value,
    placeholder = AppText.password,
    modifier = modifier,
    onNewValue = onNewValue,
)

@Composable
fun RepeatPasswordField(
    value: String,
    modifier: Modifier = Modifier,
    onNewValue: (String) -> Unit,
) = PasswordField(
    value = value,
    placeholder = AppText.repeat_password,
    modifier = modifier,
    onNewValue = onNewValue,
)

@Composable
private fun PasswordField(
    value: String,
    @StringRes placeholder: Int,
    modifier: Modifier = Modifier,
    onNewValue: (String) -> Unit,
) {
    var isVisible by remember {
        mutableStateOf(value = false)
    }

    val icon = if (isVisible) {
        painterResource(AppIcon.ic_visibility_on)
    } else {
        painterResource(AppIcon.ic_visibility_off)
    }

    val visualTransformation = if (isVisible) {
        VisualTransformation.None
    } else {
        PasswordVisualTransformation()
    }

    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = {
            onNewValue(it)
        },
        placeholder = {
            Text(text = stringResource(id = placeholder))
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = "Lock",
            )
        },
        trailingIcon = {
            IconButton(onClick = { isVisible = !isVisible }) {
                Icon(
                    painter = icon,
                    contentDescription = "Visibility",
                )
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = visualTransformation,
    )
}

@Preview(showBackground = true)
@Composable
private fun TextFieldComposable_Preview() {
    Column(modifier = Modifier.padding(all = 16.dp)) {
        BasicField(text = AppName, value = "A basic text field...") {}
        Spacer(modifier = Modifier.height(height = 16.dp))
        EmailField(value = "Email") {}
        Spacer(modifier = Modifier.height(height = 16.dp))
        PasswordField(value = "Password") {}
        Spacer(modifier = Modifier.height(height = 16.dp))
        RepeatPasswordField(value = "Confirm Password") {}
    }
}
