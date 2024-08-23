package phonpv.kotlin.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import phonpv.kotlin.home.Login.ManHinhChao
import phonpv.kotlin.home.Login.SignIn
import phonpv.kotlin.home.Login.SignUp
import phonpv.kotlin.home.Srceen.Home


enum class ROUTE_SCREEN_NAME {
    WELCOME,
    SIGNIN,
    SIGNUP,
    HOME
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: String,
) {
    NavHost(
        navController = navController,
        startDestination = ROUTE_SCREEN_NAME.WELCOME.name
    ) {
        composable(ROUTE_SCREEN_NAME.WELCOME.name) {
            ManHinhChao(navController)
        }
        composable(ROUTE_SCREEN_NAME.SIGNIN.name) {
            SignIn(navController = navController)
        }
        composable(ROUTE_SCREEN_NAME.SIGNUP.name) {
            SignUp(navController = navController)
        }
        composable(ROUTE_SCREEN_NAME.HOME.name) {
            Home(navController = navController)
        }
    }
}

