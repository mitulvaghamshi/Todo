package me.mitul.todo.common.composable

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.mitul.todo.common.extension.dropdownSelector
import me.mitul.todo.R.drawable.ic_check as AppIcon
import me.mitul.todo.R.string.app_name as AppName

@Composable
@ExperimentalMaterialApi
fun CardSelector(
    @StringRes label: Int,
    options: List<String>,
    selection: String,
    modifier: Modifier,
    onNewValue: (String) -> Unit,
) = Card(
    backgroundColor = MaterialTheme.colors.onPrimary,
    modifier = modifier,
) {
    DropdownSelector(
        label = label,
        options = options,
        selection = selection,
        modifier = Modifier.dropdownSelector(),
        onNewValue = onNewValue,
    )
}

@ExperimentalMaterialApi
@Composable
fun RegularCardEditor(
    @StringRes title: Int,
    @DrawableRes icon: Int,
    content: String,
    modifier: Modifier,
    onEditClick: () -> Unit,
) = CardEditor(
    title = title,
    icon = icon,
    content = content,
    onEditClick = onEditClick,
    highlightColor = MaterialTheme.colors.onSurface,
    modifier = modifier,
)

@ExperimentalMaterialApi
@Composable
fun DangerousCardEditor(
    @StringRes title: Int,
    @DrawableRes icon: Int,
    content: String,
    modifier: Modifier,
    onEditClick: () -> Unit,
) = CardEditor(
    title = title,
    icon = icon,
    content = content,
    onEditClick = onEditClick,
    highlightColor = MaterialTheme.colors.primary,
    modifier = modifier,
)

@ExperimentalMaterialApi
@Composable
private fun CardEditor(
    @StringRes title: Int,
    @DrawableRes icon: Int,
    content: String,
    highlightColor: Color,
    modifier: Modifier,
    onEditClick: () -> Unit,
) = Card(
    backgroundColor = MaterialTheme.colors.onPrimary,
    modifier = modifier,
    onClick = onEditClick,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(modifier = Modifier.weight(weight = 1f)) {
            Text(
                text = stringResource(id = title),
                color = highlightColor,
            )
        }

        if (content.isNotBlank()) {
            Text(
                text = content,
                modifier = Modifier.padding(
                    horizontal = 16.dp,
                    vertical = 0.dp,
                ),
            )
        }

        Icon(
            painter = painterResource(id = icon),
            contentDescription = "Icon",
            tint = highlightColor,
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
private fun CardComposable_Preview() {
    Column(modifier = Modifier.padding(all = 16.dp)) {
        CardSelector(
            label = AppName,
            options = listOf("Apple", "Banana", "Mango"),
            selection = "Mango",
            modifier = Modifier,
        ) {}

        Spacer(modifier = Modifier.height(16.dp))

        RegularCardEditor(
            title = AppName,
            icon = AppIcon,
            content = "This is Mango!",
            modifier = Modifier,
        ) {}

        Spacer(modifier = Modifier.height(16.dp))

        DangerousCardEditor(
            title = AppName,
            icon = AppIcon,
            content = "This is Apple!",
            modifier = Modifier,
        ) {}

        Spacer(modifier = Modifier.height(16.dp))

        CardEditor(
            title = AppName,
            icon = AppIcon,
            content = "This is basic Card",
            highlightColor = Color.Green,
            modifier = Modifier,
        ) {}
    }
}
