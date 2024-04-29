package iago.tikray.tikrayv4.Vistas

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MenuNavegacion(modifier: Modifier) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(90.dp)) {
        Row {

            Icon(imageVector = Icons.Filled.Home, contentDescription = "botonHome", tint = Color.White, modifier = Modifier
                .padding(start = 80.dp)
                .size(100.dp))

            Icon(imageVector = Icons.Filled.Settings, contentDescription = "botonHome", tint = Color.White, modifier = Modifier
                .padding(start = 80.dp)
                .size(100.dp))

        }


    }



}

@Preview
@Composable
private fun Preview() {
    MenuNavegacion(Modifier.fillMaxSize())

}