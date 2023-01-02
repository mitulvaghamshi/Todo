package me.mitul.todo.screens.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.delay
import me.mitul.todo.common.SPLASH_TIMEOUT
import me.mitul.todo.common.composable.BasicButton
import me.mitul.todo.common.extension.basicButton
import me.mitul.todo.R.string as AppText

@Composable
fun SplashScreen(
    popUpAndPush: (String, String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SplashViewModel = hiltViewModel(),
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background)
            .verticalScroll(state = rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (viewModel.showError.value) {
            Text(text = stringResource(id = AppText.generic_error))
            BasicButton(
                text = AppText.try_again,
                modifier = Modifier.basicButton()
            ) {
                viewModel.onAppStart(popUpAndPush = popUpAndPush)
            }
        } else {
            CircularProgressIndicator(color = MaterialTheme.colors.onBackground)
        }
    }
    LaunchedEffect(true) {
        delay(timeMillis = SPLASH_TIMEOUT)
        viewModel.onAppStart(popUpAndPush = popUpAndPush)
    }
}
