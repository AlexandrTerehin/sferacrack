import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import domain.di.DomainDI
import presentation.viewmodels.SourceCodeViewModel
import presentation.views.SourceCodeScreen

@Composable
@Preview
internal fun App() {
    SourceCodeScreen(
        viewModel = SourceCodeViewModel(
            getListPullRequest = DomainDI.getListPullRequest()
        )
    )
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Sfera Crack") {
        App()
    }
}
