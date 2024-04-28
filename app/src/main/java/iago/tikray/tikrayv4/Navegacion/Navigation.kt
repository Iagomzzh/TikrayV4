package iago.tikray.tikrayv4.Navegacion

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import iago.tikray.tikrayv4.Vistas.FormularioDeAlta.Formulario
import iago.tikray.tikrayv4.Vistas.FormularioDeAlta.FormularioDeAltaViewModel
import iago.tikray.tikrayv4.Vistas.Login.LoginViewModel
import iago.tikray.tikrayv4.Vistas.Login.ScreenLogin
import iago.tikray.tikrayv4.Vistas.MenuEntrada.MenuEntrada
import iago.tikray.tikrayv4.Vistas.MenuEntrada.MenuEntradaViewModel
import iago.tikray.tikrayv4.Vistas.Register.Register
import iago.tikray.tikrayv4.Vistas.Register.RegisterViewModel
import iago.tikray.tikrayv4.Vistas.PaginaPrincipal.ScreenPrincipal
import iago.tikray.tikrayv4.Vistas.RecuperarContrasenya.PaginaContrasenyaOlvidada
import iago.tikray.tikrayv4.Vistas.Splash.Splash
import iago.tikray.tikrayv4.Vistas.Splash.SplashViewModel
import iago.tikray.tikrayv4.ui.theme.TikrayV4Theme


@AndroidEntryPoint
class MainActivity:ComponentActivity() {

    private val registerViewModel: RegisterViewModel by viewModels()
    private val loginViewModel: LoginViewModel by viewModels()
    private val splashViewModel: SplashViewModel by viewModels()
    private val menuEntradaViewModel: MenuEntradaViewModel by viewModels()
    private val formularioDeAltaViewModel: FormularioDeAltaViewModel by viewModels()













    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TikrayV4Theme {

                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    menuEntradaViewModel.imprimirInformacion("NombreCompleto")
                    menuEntradaViewModel.imprimirInformacion("PuestoTrabajo")
                    menuEntradaViewModel.imprimirInformacion("address")
                    menuEntradaViewModel.imprimirInformacion("horaFinal")
                    menuEntradaViewModel.imprimirInformacion("horaInicio")
                    menuEntradaViewModel.imprimirInformacion("telefono")

                    menuEntradaViewModel.obtenerSizeDeDocument()

                    val navigationController = rememberNavController()

                    NavHost(navController = navigationController, startDestination = Ruta.PaginaSplash.route) {

                        // RUTAS PARA LA NAVEGACION

                        composable(Ruta.PaginaPrincipal.route) { ScreenPrincipal(navigationController) }
                        composable(Ruta.PaginaRegister.route) { Register( registerViewModel) }
                        composable(Ruta.PaginaLogin.route){ ScreenLogin(navigationController, loginViewModel) }
                        composable(Ruta.PaginaRecuperarContrasenya.route){ PaginaContrasenyaOlvidada() }
                        composable(Ruta.PaginaSplash.route){ Splash(navigationController, splashViewModel) }
                        composable(Ruta.MenuEntrada.route){ MenuEntrada(menuEntradaViewModel ) }
                        composable(Ruta.FormularioDeAlta.route){ Formulario(formularioDeAltaViewModel, navigationController) }

                        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                    }
                }
            }
        }
    }
}




//    val storage = Firebase.storage

//    @Composable
//    fun SubirFoto(){
//        var uri: Uri? by remember { mutableStateOf(null) }
//        var intentCameraLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) {
//            if (it &&  uri?.path?.isNotEmpty() == true){
//
//            }
//        }
//
//
//
//    }
//
//    private fun generated(): Uri {
//        return FileProvider.getUriForFile(Objects.requireNonNull(this),
//            "iago.tikray.tikrayv4.provider", createFile())
//
//
//    }
//
//private fun createFile(): File {
//    val name:String = SimpleDateFormat("yyyyMMdd_hhmmss").format(Date()) +  "image"
//    return File.createTempFile(name, ".jpg", externalCacheDir)
//
//}




