package uikit.components.cells

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import uikit.components.buttons.IconButtonView
import uikit.dimens.d24
import uikit.dimens.d4
import uikit.dimens.d8
import uikit.icons.SIcon
import uikit.states.SIconState
import uikit.theme.BackgroundBaseColor
import uikit.theme.TextSubtitleColor
import uikit.theme.TextTitleColor

data class IconProperty(val icon: SIcon, val state: SIconState)

@Composable
fun RowTitleSubtitleIconCell(
    modifier: Modifier = Modifier,
    icon: SIcon,
    onClickIcon: () -> Unit,
    onClick: () -> Unit,
    title: String,
    subtitle: String,
    property: List<IconProperty>? = null
) {
    Row(modifier = modifier.fillMaxWidth().background(color = BackgroundBaseColor)) {
        Column(modifier = modifier.align(Alignment.CenterVertically)) {
            IconButtonView(
                modifier = modifier.align(Alignment.CenterHorizontally),
                icon = icon,
                onClick = onClickIcon
            )
            Row {
                property?.forEach {
                    val tint = when (it.state) {
                        SIconState.GOOD -> Color.Green
                        SIconState.BAD -> Color.Red
                    }

                    Icon(
                        painter = painterResource(it.icon.value),
                        contentDescription = null,
                        modifier = modifier.size(d24),
                        tint = tint
                    )
                    Spacer(modifier = Modifier.width(d4))
                }
            }
        }

        Column(
            modifier = modifier
                .fillMaxWidth()
                .align(alignment = Alignment.CenterVertically)
                .padding(start = d8)
                .clickable { onClick() }
        ) {
            Text(text = title, color = TextTitleColor)
            Text(text = subtitle, color = TextSubtitleColor)
        }
    }
}

@Composable
@Preview
private fun RowTitleSubtitleIconCellPreview() {
    RowTitleSubtitleIconCell(
        icon = SIcon.FAVORITE,
        onClickIcon = {},
        onClick = {},
        title = "Title",
        subtitle = "Subtitle",
        property = listOf(
            IconProperty(SIcon.BUILD, SIconState.GOOD),
            IconProperty(SIcon.NEW_RELEASE, SIconState.BAD),
            IconProperty(SIcon.RELEASE_ALERT, SIconState.BAD)
        )
    )
}