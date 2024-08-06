package uikit.components.buttons

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import uikit.icons.SIcon

@Composable
fun IconButtonView(modifier: Modifier = Modifier, icon: SIcon, onClick: () -> Unit) {
    IconButton(modifier = modifier, onClick = onClick) {
        Icon(painter = painterResource(icon.value), contentDescription = "")
    }
}

@Composable
@Preview
private fun IconButtonViewPreview() {
    IconButtonView(Modifier.fillMaxSize(), icon = SIcon.FAVORITE, onClick = {})
}