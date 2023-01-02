package me.mitul.todo.common.extension

import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

fun Modifier.textButton(): Modifier = this
    .fillMaxWidth()
    .padding(
        start = 16.dp,
        top = 8.dp,
        end = 16.dp,
        bottom = 0.dp,
    )

fun Modifier.basicButton(): Modifier = this
    .fillMaxWidth()
    .padding(
        horizontal = 16.dp,
        vertical = 8.dp,
    )

fun Modifier.card(): Modifier = this.padding(
    start = 16.dp,
    top = 0.dp,
    end = 16.dp,
    bottom = 8.dp,
)

fun Modifier.contextMenu(): Modifier = this.wrapContentWidth()

fun Modifier.dropdownSelector(): Modifier = this.fillMaxWidth()

fun Modifier.fieldModifier(): Modifier = this
    .fillMaxWidth()
    .padding(
        horizontal = 16.dp,
        vertical = 4.dp,
    )

fun Modifier.toolbarActions(): Modifier = this.wrapContentSize(align = Alignment.TopEnd)

fun Modifier.spacer(): Modifier = this
    .fillMaxWidth()
    .padding(all = 12.dp)

fun Modifier.smallSpacer(): Modifier = this
    .fillMaxWidth()
    .height(height = 8.dp)
