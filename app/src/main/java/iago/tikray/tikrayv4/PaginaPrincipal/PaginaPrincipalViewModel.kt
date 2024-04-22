package iago.tikray.tikrayv4.PaginaPrincipal

import androidx.navigation.NavController
import iago.tikray.tikrayv4.Navegacion.Ruta


// BOTON PARA NAVEGAR DESDE LA PAGINA PRINCIPAL AL REGISTER
fun navegarAlRegister(navigationController: NavController): () -> Unit {
    val nav: Any = navigationController.navigate(Ruta.PaginaRegister.route)
    return { nav }

}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// BOTON PARA NAVEGAR DESDE LA PAGINA PRINCIPAL AL LOGIN

fun navegarAlLogin(navigationController: NavController): () -> Unit {
    val nav: Any = navigationController.navigate(Ruta.PaginaLogin.route)
    return { nav }
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// AÑADE AQUI MAS BOTONES DE NAVEGACIÓN

