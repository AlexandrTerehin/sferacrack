package uikit.components.buttons

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun BaseButton(modifier: Modifier = Modifier, onClick: () -> Unit, text: String) {
    Button(onClick = onClick, modifier = modifier) {
        Text(text = text)
    }
}

@Composable
@Preview
private fun BaseButtonPreview() {
    BaseButton(onClick = {}, text = "login")
}