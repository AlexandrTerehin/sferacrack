package presentation.components.cells

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import core.consts.ProjectKeysConsts
import core.consts.UrlConsts
import domain.models.PullRequest
import uikit.dimens.d16
import uikit.dimens.d8
import uikit.theme.BackgroundCloseColor
import uikit.theme.BackgroundOpenColor
import java.awt.Desktop
import java.net.URI

@Composable
fun pullRequestCell(pr: PullRequest) {
    Card(
        backgroundColor = if (pr.closed == true) BackgroundCloseColor else BackgroundOpenColor,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = d16)
            .clickable {
                Desktop.getDesktop()
                    .browse(URI("${UrlConsts.SFERA}sourcecode/projects/${ProjectKeysConsts.PROJECT_KEY_ANDROID}/repos/${ProjectKeysConsts.REPO_NAME_ANDROID}/pulls/${pr.id}"))
            }) {
        Column(modifier = Modifier.padding(d8)) {
            Text(
                maxLines = 1,
                text = pr.title.orEmpty()
            )
            Text(
                modifier = Modifier.padding(top = d8),
                text = pr.description.orEmpty()
            )
            Text(
                modifier = Modifier.padding(top = d8),
                color = Color.Green,
                text = "${pr.authorEmail} | ${pr.authorLogin}"
            )
        }
    }
}

@Composable
@Preview
private fun pullRequestCellPreview() {
    pullRequestCell(
        pr = PullRequest(
            id = 136777,
            authorLogin = "",
            authorEmail = "",
            authorFirstName = "",
            authorMiddleName = "",
            authorLastName = "",
            authorFullName = "",
            title = "SMSAVINGS-7093 [Android] Яндекс метрика и Ключ Астрома. Событие успеха после ошибки",
            description = "https://sfera.inno.local/tasks/task/SMSAVINGS-7093",
            closed = false,
            reviewers = null
        )
    )
}