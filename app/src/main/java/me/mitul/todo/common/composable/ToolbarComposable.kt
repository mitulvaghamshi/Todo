package me.mitul.todo.common.composable

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.mitul.todo.R.drawable.ic_settings as AppSetting
import me.mitul.todo.R.string.app_name as AppName

@Composable
fun BasicToolbar(
    @StringRes title: Int,
) = TopAppBar(
    title = {
        Text(stringResource(title))
    },
    backgroundColor = toolbarColor()
)

@Composable
fun ActionToolbar(
    @StringRes title: Int,
    @DrawableRes endActionIcon: Int,
    modifier: Modifier = Modifier,
    endAction: () -> Unit,
) = TopAppBar(
    title = {
        Text(stringResource(title))
    },
    backgroundColor = toolbarColor(),
    actions = {
        Box(modifier = modifier) {
            IconButton(onClick = endAction) {
                Icon(
                    painter = painterResource(id = endActionIcon),
                    contentDescription = "Action",
                )
            }
        }
    }
)

@Composable
private fun toolbarColor(
    darkTheme: Boolean = isSystemInDarkTheme(),
): Color = if (darkTheme) {
    MaterialTheme.colors.secondary
} else {
    MaterialTheme.colors.primaryVariant
}

@Preview(showBackground = true)
@Composable
private fun ToolBarComposable_Preview() {
    Column(modifier = Modifier.padding(all = 16.dp)) {
        BasicToolbar(title = AppName)
        Spacer(modifier = Modifier.height(16.dp))
        ActionToolbar(
            title = AppName,
            endActionIcon = AppSetting,
        ) {}
    }
}
