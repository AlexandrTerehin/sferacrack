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
import presentation.components.cells.pullRequestCell
import presentation.viewmodels.SourceCodeViewModel
import uikit.dimens.d16
import uikit.dimens.d4
import uikit.dimens.d8

@Composable
@Preview
internal fun App(
    viewModel: SourceCodeViewModel = viewModel()
) {
    pullRequestView(
        pullRequetsState = viewModel.flowPullRequest.collectAsState(emptyList()),
        openState = viewModel.openState.collectAsState(),
        authorState = viewModel.authorState.collectAsState(),
        reviewerState = viewModel.reviewerState.collectAsState(),
        onSwitchAuthor = viewModel::switchAuthor,
        onSwitchReviewer = viewModel::switchReviewer,
        onSwitchOpen = viewModel::switchOpen
    )
}

@Composable
internal fun pullRequestView(
    pullRequetsState: State<List<PullRequest>>,
    openState: State<Boolean>,
    authorState: State<Boolean>,
    reviewerState: State<Boolean>,
    onSwitchAuthor: () -> Unit,
    onSwitchReviewer: () -> Unit,
    onSwitchOpen: () -> Unit
) {
    MaterialTheme {
        Column {
            Row(modifier = Modifier.fillMaxWidth()) {
                Checkbox(checked = openState.value, onCheckedChange = { onSwitchOpen() })
                Text(text = "Open", modifier = Modifier.align(alignment = Alignment.CenterVertically))
                Divider(modifier = Modifier.width(d16))
                Checkbox(checked = authorState.value, onCheckedChange = { onSwitchAuthor() })
                Text(text = "Author", modifier = Modifier.align(alignment = Alignment.CenterVertically))
                Divider(modifier = Modifier.width(d16))
                Checkbox(
                    checked = reviewerState.value,
                    onCheckedChange = { onSwitchReviewer() })
                Text(text = "Reviewer", modifier = Modifier.align(alignment = Alignment.CenterVertically))
            }

            LazyColumn(modifier = Modifier.padding(top = d8)) {
                item {
                    pullRequetsState.value.forEach {
                        pullRequestCell(it)
                        Divider(modifier = Modifier.fillMaxWidth().height(d4))
                    }
                }
            }
        }

    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App(
            viewModel = SourceCodeViewModel(
                getListPullRequest = DomainDI.getListPullRequest()
            )
        )
    }
}
