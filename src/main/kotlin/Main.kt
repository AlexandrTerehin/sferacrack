import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Checkbox
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.lifecycle.viewmodel.compose.viewModel
import domain.di.DomainDI
import domain.models.PullRequest
import presentation.viewmodels.SourceCodeViewModel
import uikit.components.cells.IconProperty
import uikit.components.cells.RowTitleSubtitleIconCell
import uikit.dimens.d16
import uikit.dimens.d4
import uikit.dimens.d8
import uikit.icons.SIcon
import uikit.states.SIconState

@Composable
@Preview
internal fun App(
    viewModel: SourceCodeViewModel = viewModel()
) {
    pullRequestView(
        pullRequetsState = viewModel.flowPullRequest.collectAsState(emptyList()),
        authorState = viewModel.authorState.collectAsState(),
        reviewerState = viewModel.reviewerState.collectAsState(),
        onSwitchAuthor = viewModel::switchAuthor,
        onSwitchReviewer = viewModel::switchReviewer,
        onClickPullRequest = viewModel::openPullRequest
    )

    viewModel.init()
}

@Composable
internal fun pullRequestView(
    pullRequetsState: State<List<PullRequest>>,
    authorState: State<Boolean>,
    reviewerState: State<Boolean>,
    onSwitchAuthor: () -> Unit,
    onSwitchReviewer: () -> Unit,
    onClickPullRequest: (PullRequest) -> Unit
) {
    MaterialTheme {
        Column {
            Row(modifier = Modifier.fillMaxWidth()) {
                Checkbox(checked = authorState.value, onCheckedChange = { onSwitchAuthor() })
                Text(text = "Author", modifier = Modifier.align(alignment = Alignment.CenterVertically))
                Divider(modifier = Modifier.width(d16))
                Checkbox(
                    checked = reviewerState.value,
                    onCheckedChange = { onSwitchReviewer() })
                Text(text = "Reviewer", modifier = Modifier.align(alignment = Alignment.CenterVertically))
            }

            LazyColumn(modifier = Modifier.padding(top = d8), userScrollEnabled = true) {
                item {
                    pullRequetsState.value.forEach {
                        RowTitleSubtitleIconCell(
                            icon = SIcon.FAVORITE,
                            onClickIcon = { },
                            onClick = { onClickPullRequest(it) },
                            title = it.title.orEmpty(),
                            subtitle = it.description.orEmpty(),
                            property = listOf(
                                IconProperty(SIcon.BUILD, SIconState.GOOD),
                                IconProperty(SIcon.NEW_RELEASE, SIconState.BAD),
                                IconProperty(SIcon.RELEASE_ALERT, SIconState.BAD)
                            )
                        )
                        Divider(modifier = Modifier.fillMaxWidth().height(d4))
                    }
                }
            }
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Sfera Crack") {
        App(
            viewModel = SourceCodeViewModel(
                getListPullRequest = DomainDI.getListPullRequest()
            )
        )
    }
}
