package me.mitul.todo.screens.settings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import me.mitul.todo.common.composable.*
import me.mitul.todo.common.extension.card
import me.mitul.todo.common.extension.spacer
import me.mitul.todo.R.drawable as AppIcon
import me.mitul.todo.R.string as AppText

@ExperimentalMaterialApi
@Composable
fun SettingsScreen(
    restartApp: (String) -> Unit,
    push: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SettingsViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState(initial = SettingsUiState(false))

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BasicToolbar(title = AppText.settings)

        Spacer(modifier = Modifier.spacer())

        if (uiState.isAnonymousAccount) {
            RegularCardEditor(
                title = AppText.sign_in,
                icon = AppIcon.ic_sign_in,
                content = "",
                modifier = Modifier.card(),
            ) {
                viewModel.onLoginClick(push = push)
            }

            RegularCardEditor(
                title = AppText.create_account,
                icon = AppIcon.ic_create_account,
                content = "",
                modifier = Modifier.card(),
            ) {
                viewModel.onSignUpClick(push = push)
            }
        } else {
            SignOutCard {
                viewModel.onSignOutClick(restartApp = restartApp)
            }

            DeleteMyAccountCard {
                viewModel.onDeleteMyAccountClick(navigate = restartApp)
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
private fun SignOutCard(signOut: () -> Unit) {
    var showWarningDialog by remember { mutableStateOf(false) }

    RegularCardEditor(
        title = AppText.sign_out,
        icon = AppIcon.ic_exit,
        content = "",
        modifier = Modifier.card(),
    ) {
        showWarningDialog = true
    }

    if (showWarningDialog) {
        AlertDialog(
            title = {
                Text(text = stringResource(id = AppText.sign_out_title))
            },
            text = {
                Text(text = stringResource(id = AppText.sign_out_description))
            },
            dismissButton = {
                DialogCancelButton(text = AppText.cancel) {
                    showWarningDialog = false
                }
            },
            confirmButton = {
                DialogConfirmButton(text = AppText.sign_out) {
                    signOut()
                    showWarningDialog = false
                }
            },
            onDismissRequest = {
                showWarningDialog = false
            },
        )
    }
}

@ExperimentalMaterialApi
@Composable
private fun DeleteMyAccountCard(deleteMyAccount: () -> Unit) {
    var showWarningDialog by remember {
        mutableStateOf(value = false)
    }

    DangerousCardEditor(
        title = AppText.delete_my_account,
        icon = AppIcon.ic_delete_my_account,
        content = "",
        modifier = Modifier.card(),
    ) {
        showWarningDialog = true
    }

    if (showWarningDialog) {
        AlertDialog(
            title = {
                Text(text = stringResource(id = AppText.delete_account_title))
            },
            text = {
                Text(text = stringResource(id = AppText.delete_account_description))
            },
            dismissButton = {
                DialogCancelButton(text = AppText.cancel) {
                    showWarningDialog = false
                }
            },
            confirmButton = {
                DialogConfirmButton(text = AppText.delete_my_account) {
                    deleteMyAccount()
                    showWarningDialog = false
                }
            },
            onDismissRequest = {
                showWarningDialog = false
            },
        )
    }
}
