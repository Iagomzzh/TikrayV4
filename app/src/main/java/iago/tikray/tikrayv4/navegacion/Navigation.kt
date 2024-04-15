package iago.tikray.tikrayv4.navegacion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import iago.tikray.tikrayv4.Login.LoginViewModel
import iago.tikray.tikrayv4.Login.ScreenLogin
import iago.tikray.tikrayv4.Register.Register
import iago.tikray.tikrayv4.Register.RegisterViewModel
import iago.tikray.tikrayv4.PaginaPrincipal.ScreenPrincipal
import iago.tikray.tikrayv4.RecuperarContrasenya.PaginaContrasenyaOlvidada
import iago.tikray.tikrayv4.ui.theme.TikrayV4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TikrayV4Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navigationController = rememberNavController()
                    NavHost(navController = navigationController, startDestination = Ruta.PaginaPrincipal.route  ) {
                        composable(Ruta.PaginaPrincipal.route) { ScreenPrincipal(navigationController) }
                        composable(Ruta.PaginaRegister.route) { Register(navigationController, RegisterViewModel()) }
                        composable(Ruta.PaginaLogin.route){ ScreenLogin(navigationController, LoginViewModel())}
                        composable(Ruta.PaginaRecuperarContrasenya.route){ PaginaContrasenyaOlvidada(navigationController)}
                    }


                }
            }
        }
    }
}








