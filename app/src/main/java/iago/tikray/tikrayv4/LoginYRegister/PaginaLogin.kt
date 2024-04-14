package iago.tikray.tikrayv4.LoginYRegister

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController

@Composable
fun ScreenLogin(navigationController: NavHostController) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        Text(text = "LOGIN")
    }
    
}