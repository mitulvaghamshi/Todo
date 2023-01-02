package me.mitul.todo.common.composable

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.mitul.todo.R.string.app_name as AppName

@Composable
fun BasicTextButton(
    @StringRes text: Int,
    modifier: Modifier = Modifier,
    action: () -> Unit,
) = TextButton(
    onClick = action,
    modifier = modifier,
) {
    Text(text = stringResource(id = text))
}

@Composable
fun BasicButton(
    @StringRes text: Int,
    modifier: Modifier = Modifier,
    action: () -> Unit,
) = Button(
    onClick = action,
    modifier = modifier,
    colors = ButtonDefaults.buttonColors(
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onPrimary,
    ),
) {
    Text(
        text = stringResource(id = text),
        fontSize = 16.sp,
    )
}

@Composable
fun DialogConfirmButton(
    @StringRes text: Int,
    action: () -> Unit,
) = Button(
    onClick = action,
    colors = ButtonDefaults.buttonColors(
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onPrimary,
    )
) {
    Text(text = stringResource(id = text))
}

@Composable
fun DialogCancelButton(
    @StringRes text: Int,
    action: () -> Unit,
) = Button(
    onClick = action,
    colors = ButtonDefaults.buttonColors(
        backgroundColor = MaterialTheme.colors.onPrimary,
        contentColor = MaterialTheme.colors.primary,
    )
) {
    Text(text = stringResource(id = text))
}

@Preview(showBackground = true)
@Composable
private fun ButtonComposable_Preview() {
    Column(modifier = Modifier.padding(all = 16.dp)) {
        BasicTextButton(text = AppName) {}
        BasicButton(text = AppName) {}
        DialogConfirmButton(text = AppName) {}
        DialogCancelButton(text = AppName) {}
    }
}
