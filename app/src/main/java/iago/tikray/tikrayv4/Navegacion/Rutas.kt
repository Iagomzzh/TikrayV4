package iago.tikray.tikrayv4.Navegacion

sealed class Ruta(val route:String) {

    // RUTAS
    object PaginaPrincipal:Ruta("PaginaPrincipal")
    object PaginaRegister:Ruta("PaginaRegister")
    object PaginaLogin:Ruta("PaginaLogin")
    object  PaginaRecuperarContrasenya:Ruta("RecuperarPassword")

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}