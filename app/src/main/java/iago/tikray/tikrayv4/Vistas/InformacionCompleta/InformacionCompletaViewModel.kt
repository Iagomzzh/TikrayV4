package iago.tikray.tikrayv4.Vistas.InformacionCompleta

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import iago.tikray.tikrayv4.Vistas.MenuEntrada.MenuEntradaViewModel
import javax.inject.Inject


@HiltViewModel
class InformacionCompletaViewModel @Inject constructor(menuEntradaViewModel: MenuEntradaViewModel): ViewModel(){

    val hola = menuEntradaViewModel.address



}