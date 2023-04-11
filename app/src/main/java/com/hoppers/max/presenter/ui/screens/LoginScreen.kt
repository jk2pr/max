package com.hoppers.max.presenter.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.hoppers.max.MainActivity
import com.hoppers.max.R
import com.hoppers.max.constants.Paddings
import com.hoppers.max.data.UiState
import com.hoppers.max.extensions.showToast
import com.hoppers.max.localproviders.LocalNavController
import com.hoppers.max.presenter.ui.component.AuthenticationButton
import com.hoppers.max.presenter.ui.component.Loader
import com.hoppers.max.presenter.ui.component.Page
import com.hoppers.max.presenter.ui.component.TextButton
import com.hoppers.max.presenter.ui.navigations.Graph
import com.hoppers.max.presenter.viewmodels.LoginViewModel

@Composable
fun LoginScreen() {
    val context = LocalContext.current as MainActivity
    val localNavController = LocalNavController.current
    val loginViewModel = hiltViewModel<LoginViewModel>()
    //by context.viewModels()

    Page {
        when (val result = loginViewModel.state.collectAsState().value) {
            is UiState.Content -> {
                /* Login success*/
                LaunchedEffect(key1 = Unit) {
                    localNavController.navigate(Graph.HOME) {
                        popUpTo(Graph.AUTHENTICATION) {
                            inclusive = true
                        }
                    }
                }
            }
            is UiState.Error -> {/* Login Fail */
                Toast.makeText(context, result.message, Toast.LENGTH_LONG).show()
                loginViewModel.resetState()
            }
            UiState.Loading -> /*Loading*/ Loader()
            UiState.Empty -> {
                DefaultUI(jobViewModel = loginViewModel, context = context)
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DefaultUI(jobViewModel: LoginViewModel, context: MainActivity) {
    Column(
        modifier = Modifier
            .padding(Paddings.DP_24)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val username = remember { mutableStateOf(TextFieldValue()) }
        val password = remember { mutableStateOf(TextFieldValue()) }
        var isUserNameError by remember { mutableStateOf(false) }
        var isPasswordError by remember { mutableStateOf(false) }
        Text(
            text = "Welcome to ${stringResource(id = R.string.app_name)}",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(48.dp))
        OutlinedTextField(
            isError = isUserNameError,
            label = { Text(text = "Username") },
            value = username.value,
            onValueChange = {
                isUserNameError = false
                username.value = it
            },
        )

        Spacer(modifier = Modifier.height(24.dp))
        OutlinedTextField(
            isError = isPasswordError,
            label = { Text(text = "Password") },
            value = password.value,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = {
                isPasswordError = false
                password.value = it
            })

        Spacer(modifier = Modifier.height(24.dp))
        AuthenticationButton(

            onClick = {
                isUserNameError = username.value.text.isEmpty()
                isPasswordError = password.value.text.isEmpty()
                if (isUserNameError || isPasswordError) {
                    context.showToast("Fields can't empty")
                    return@AuthenticationButton
                }
                jobViewModel.doLogin(
                    username = username.value.text,
                    password = password.value.text
                )
            }
        )
        Spacer(modifier = Modifier.height(24.dp))
        TextButton(
            text = "Forgot password ?",
            onClick = { context.showToast("Coming soon") },
            style = MaterialTheme.typography.bodyMedium.copy(
                textDecoration = TextDecoration.Underline
            )
        )
        TextButton(
            text = "Sign up here",
            onClick = { context.showToast("Coming soon") },
            modifier = Modifier
                .padding(24.dp),
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 14.sp,
                textDecoration = TextDecoration.Underline,
                color = MaterialTheme.colorScheme.primary
            )
        )
    }
}

@Preview
@Composable
fun Preview(
) {
    LoginScreen()
}




