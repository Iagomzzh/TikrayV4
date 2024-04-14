package iago.tikray.tikrayv4.navegacion

sealed class Ruta(val route:String) {
    object PaginaPrincipal:Ruta("PaginaPrincipal")
    object PaginaRegister:Ruta("PaginaRegister")
    object PaginaLogin:Ruta("PaginaLogin")

}