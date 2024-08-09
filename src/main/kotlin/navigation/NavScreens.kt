package navigation

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import presentation.views.AuthorizationScreen

@Composable
internal fun NavScreens(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screens.AUTHORIZATION.name,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(route = Screens.AUTHORIZATION.name) {
            AuthorizationScreen()
        }
    }
}

@Composable
@Preview
private fun NavScreensPreview() {
    NavScreens()
}