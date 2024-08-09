package presentation.views

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import navigation.Screens
import presentation.viewmodels.AuthorizationViewModel
import uikit.components.buttons.BaseButton
import uikit.components.inputs.OutlineInput
import uikit.dimens.d16

@Composable
internal fun AuthorizationScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    viewModel: AuthorizationViewModel = viewModel(factory = AuthorizationViewModel.Factory)
) {
    AuthorizationScreenView(
        modifier = modifier,
        navHostController = navHostController,
        stateIsAuthorization = viewModel.flowIsAuthorization.collectAsState(),
        onCheck = viewModel::checkAuthorization,
        onClear = viewModel::clear
    )
}

@Composable
private fun AuthorizationScreenView(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    stateIsAuthorization: State<Boolean?>,
    onCheck: (String, String, String) -> Unit,
    onClear: () -> Unit
) {
    val login = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }

    Card(
        modifier = modifier
            .padding(d16)
            .fillMaxSize()
    ) {
        Box {
            Column(modifier = modifier.padding(d16).align(alignment = Alignment.Center)) {
                OutlineInput(modifier = modifier, label = "login:", value = login, placeholder = "VTB*******@corp.dev.vtb")
                OutlineInput(modifier = modifier, label = "password:", value = password)
                OutlineInput(modifier = modifier, label = "email:", value = email, placeholder = "email@dev.vtb.ru")
                BaseButton(modifier = modifier, onClick = {
                    onCheck(login.value, password.value, email.value)
                }, text = "Authorization")
            }
        }
    }

    if (stateIsAuthorization.value == false) {
        AlertDialog(
            onDismissRequest = { },
            confirmButton = {
                Button(onClick = { onClear() }) {
                    Text(text = "close")
                }
            },
            title = { Text(text = "Warning") },
            text = { Text(text = "Authorization error") }
        )
    }

    if (stateIsAuthorization.value == true) {
        navHostController.navigate(route = Screens.PULL_REQUESTS.name)
    }
}

@Composable
@Preview
private fun AuthorizationsScreenPreview() {
    AuthorizationScreen(
        navHostController = NavHostController(), viewModel = AuthorizationViewModel()
    )
}