package me.mitul.todo.common.composable

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.mitul.todo.R

@Composable
@ExperimentalMaterialApi
fun DropdownContextMenu(
    options: List<String>,
    modifier: Modifier,
    onActionClick: (String) -> Unit,
) {
    var isExpanded by remember {
        mutableStateOf(value = false)
    }

    ExposedDropdownMenuBox(
        expanded = isExpanded,
        modifier = modifier,
        onExpandedChange = {
            isExpanded = !isExpanded
        }
    ) {
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = "More",
            modifier = Modifier.padding(8.dp, 0.dp),
        )

        ExposedDropdownMenu(
            modifier = Modifier.width(180.dp),
            expanded = isExpanded,
            onDismissRequest = {
                isExpanded = false
            }
        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    onClick = {
                        isExpanded = false
                        onActionClick(selectionOption)
                    }
                ) {
                    Text(text = selectionOption)
                }
            }
        }
    }
}

@Composable
@ExperimentalMaterialApi
fun DropdownSelector(
    @StringRes label: Int,
    options: List<String>,
    selection: String,
    modifier: Modifier,
    onNewValue: (String) -> Unit,
) {
    var isExpanded by remember {
        mutableStateOf(value = false)
    }

    ExposedDropdownMenuBox(
        expanded = isExpanded,
        modifier = modifier,
        onExpandedChange = {
            isExpanded = !isExpanded
        }
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            readOnly = true,
            value = selection,
            onValueChange = {},
            label = {
                Text(text = stringResource(id = label))
            },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
            },
            colors = dropdownColors()
        )

        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = {
                isExpanded = false
            },
        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    onClick = {
                        onNewValue(selectionOption)
                        isExpanded = false
                    }
                ) {
                    Text(text = selectionOption)
                }
            }
        }
    }
}

@Composable
@ExperimentalMaterialApi
private fun dropdownColors(): TextFieldColors = ExposedDropdownMenuDefaults.textFieldColors(
    backgroundColor = MaterialTheme.colors.onPrimary,
    focusedIndicatorColor = Color.Transparent,
    unfocusedIndicatorColor = Color.Transparent,
    trailingIconColor = MaterialTheme.colors.onSurface,
    focusedTrailingIconColor = MaterialTheme.colors.onSurface,
    focusedLabelColor = MaterialTheme.colors.primary,
    unfocusedLabelColor = MaterialTheme.colors.primary
)

@Preview(showBackground = true)
@Composable
@ExperimentalMaterialApi
private fun DropDownComposable_Preview() {
    Column(modifier = Modifier.padding(all = 16.dp)) {
        DropdownContextMenu(
            options = listOf("Apple", "Banana", "Mango"),
            modifier = Modifier,
        ) {}

        Spacer(modifier = Modifier.height(16.dp))

        DropdownSelector(
            label = R.string.app_name,
            options = listOf("Apple", "Banana", "Mango"),
            selection = "Apple",
            modifier = Modifier,
        ) {}
    }
}
