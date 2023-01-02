package me.mitul.todo.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import me.mitul.todo.common.composable.*
import me.mitul.todo.common.extension.basicButton
import me.mitul.todo.common.extension.fieldModifier
import me.mitul.todo.common.extension.textButton
import me.mitul.todo.R.string as AppText

@Composable
fun LoginScreen(
    openAndPopUp: (String, String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState

    BasicToolbar(title = AppText.login_details)

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EmailField(
            value = uiState.email,
            modifier = Modifier.fieldModifier(),
            onNewValue = viewModel::onEmailChange,
        )

        PasswordField(
            value = uiState.password,
            modifier = Modifier.fieldModifier(),
            onNewValue = viewModel::onPasswordChange,
        )

        BasicButton(
            text = AppText.sign_in,
            modifier = Modifier.basicButton(),
        ) {
            if (viewModel.isValid()) {
                viewModel.onSignInClick(popUpAndPush = openAndPopUp)
            }
        }
        
        BasicTextButton(
            text = AppText.forgot_password,
            modifier = Modifier.textButton(),
            action = viewModel::onForgotPasswordClick,
        )
    }
}
