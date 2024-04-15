package iago.tikray.tikrayv4.PaginaPrincipal

import androidx.navigation.NavController
import iago.tikray.tikrayv4.navegacion.Ruta

fun navegarBoton1(navigationController: NavController): () -> Unit {
    val nav: Any = navigationController.navigate(Ruta.PaginaRegister.route)
    return { nav }

}


fun navegarBoton2(navigationController: NavController): () -> Unit {
    val nav: Any = navigationController.navigate(Ruta.PaginaLogin.route)
    return { nav }
}