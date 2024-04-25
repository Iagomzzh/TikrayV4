package iago.tikray.tikrayv4.MenuEntrada

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import iago.tikray.tikrayv4.R

@Composable
fun MenuEntrada(
    menuEntradaViewModel: MenuEntradaViewModel,
    navigationController: NavHostController
) {

    ConstraintLayout(modifier = Modifier
        .fillMaxSize()
        .background(colorResource(id = R.color.tikrayColor1))){





    }
    Button(onClick = { menuEntradaViewModel.logOut()}) {

    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Preview() {
//    MenuEntrada( rememberNavController())

}