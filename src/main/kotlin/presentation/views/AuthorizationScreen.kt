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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import navigation.Screens
import uikit.components.buttons.BaseButton
import uikit.components.inputs.OutlineInput
import uikit.dimens.d16

@Composable
internal fun AuthorizationScreen(modifier: Modifier = Modifier, navHostController: NavHostController) {
    val login = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }

    val openAlert = remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .padding(d16)
            .fillMaxSize()
    ) {
        Box {
            Column(modifier = modifier.padding(d16).align(alignment = Alignment.Center)) {
                OutlineInput(modifier = modifier, label = "login:", value = login)
                OutlineInput(modifier = modifier, label = "password:", value = password)
                OutlineInput(modifier = modifier, label = "email:", value = email)
                BaseButton(modifier = modifier, onClick = {
                    navHostController.navigate(route = Screens.PULL_REQUESTS.name)
                    //openAlert.value = true
                }, text = "Authorization")
            }
        }
    }

    if (openAlert.value) {
        AlertDialog(
            onDismissRequest = { },
            confirmButton = {
                Button(onClick = { openAlert.value = false }) {
                    Text(text = "close")
                }
            },
            title = { Text(text = "Auto") },
            text = { Text(text = "${login.value}\n${password.value}") }
        )
    }
}

@Composable
@Preview
private fun AuthorizationsScreenPreview() {
    AuthorizationScreen(navHostController = NavHostController())
}