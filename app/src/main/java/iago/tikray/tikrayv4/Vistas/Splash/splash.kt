package iago.tikray.tikrayv4.Vistas.Splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.EntryPoint
import iago.tikray.tikrayv4.Firebase.AuthService
import iago.tikray.tikrayv4.Navegacion.Ruta
import iago.tikray.tikrayv4.R
import kotlinx.coroutines.delay
import javax.inject.Inject


@Composable
fun Splash(navigationController: NavHostController, splashViewModel: SplashViewModel) {
    val customTextStyle = TextStyle(
        fontFamily = FontFamily(
            Font(R.font.kodemono_semibold, FontWeight.Bold),
            Font(R.font.kodemonor_egular, FontWeight.Normal),
            Font(R.font.kodemono_bold, FontWeight.ExtraBold),
        )
    )
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.tikrayColor1))
    ) {
        val (logoDeCarga, empresaNombre) = createRefs()
        var nombreArchivo by remember { mutableStateOf(0) }

        LaunchedEffect(key1 = null) {
            while (nombreArchivo <= 95) {
                delay(25)
                // Actualiza el nombre del archivo según el progreso
                nombreArchivo += 5
            }
        }

        Image(painter = painterResource(id = getResourceIdForDrawable(nombreArchivo)),
            contentDescription = null,
            modifier = Modifier
                .size(300.dp)
                .constrainAs(logoDeCarga) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                })

        Text(
            text = "TikRay",
            style = TextStyle(
                color = Color.White,
                fontSize = 35.sp,
                fontFamily = customTextStyle.fontFamily
            ),
            modifier = Modifier.constrainAs(empresaNombre){
                start.linkTo(parent.start)
                end.linkTo(parent.end)

                bottom.linkTo(parent.bottom, margin = 150.dp)

            }
        )

        if (nombreArchivo == 100){
            LaunchedEffect(key1 = null) {
                delay(40)
                splashViewModel.checkDestination(navigationController)


                
            }

        }
    }


}

// Función para obtener el ID del recurso Drawable segun el progreso
fun getResourceIdForDrawable(progreso: Int): Int {
    val nombreImagen = "icono_web_carga_$progreso"
    return R.drawable::class.java.getField(nombreImagen).getInt(null)
}

@Preview
@Composable
private fun Preview() {
    Splash(rememberNavController(), SplashViewModel(AuthService(FirebaseAuth.getInstance())))

}
