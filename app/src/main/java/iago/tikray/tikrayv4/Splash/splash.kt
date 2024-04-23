package iago.tikray.tikrayv4.Splash

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import iago.tikray.tikrayv4.R
import kotlinx.coroutines.delay

@Composable
fun Splash() {

    ConstraintLayout(modifier = Modifier.run {
        fillMaxSize()
            .background(colorResource(id = R.color.tikrayColor1))


    }) {

        val logoDeCarga = createRef()

        var nombreArchivo by remember {
            mutableStateOf(0)
        }

        LaunchedEffect(key1 = null) {
            while (nombreArchivo <= 2131099690) {
                nombreArchivo = 2131099679 +1
                delay(90)


            }
        } //2131099679

        Image(
            painter = painterResource( {id = nombreArchivo}),
            contentDescription = null,
            Modifier.constrainAs(logoDeCarga) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            })

    }

    @Composable
    fun MoverAnimacion() {
        LaunchedEffect(key1 = null) {

            delay(90)



        }

    }


}






