package uikit.components.inputs

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun OutlineInput(
    modifier: Modifier = Modifier,
    value: MutableState<String> = remember { mutableStateOf("") },
    onValueChange: (String) -> Unit = { value.value = it },
    singleLine: Boolean = true,
    label: String = "",
    placeholder: String = ""
) {
    OutlinedTextField(
        modifier = modifier,
        value = value.value,
        onValueChange = onValueChange,
        singleLine = singleLine,
        label = { Text(text = label) },
        placeholder = { Text(text = placeholder) }
    )
}

@Composable
@Preview
private fun OutlineInputPreview() {
    OutlineInput(label = "Login")
}