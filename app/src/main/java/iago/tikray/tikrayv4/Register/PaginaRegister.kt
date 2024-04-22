package iago.tikray.tikrayv4.Register

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import iago.tikray.tikrayv4.R
@Composable
fun Register(navigationController: NavHostController, registerViewModel: RegisterViewModel) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.tikrayColor1))
    )
    {

        // Definimos las referencias para poder colocar los elementos
        val (logo, titulo, textFieldNombre, textFieldCorreo, textFieldContrasenya, progressBar, textFIeldConfirmarContrasenya, textoContrasenyasCoinciden, boton1) = createRefs()
        val margenSuperior = createGuidelineFromTop(0.25f)

        // TEXTFIELDS SUSCRITOS AL VIEWMODEL
        val name: String by registerViewModel.name.observeAsState(initial = "")
        val correo: String by registerViewModel.correo.observeAsState(initial = "")
        val contrasenya: String by registerViewModel.contrasenya.observeAsState(initial = "")
        val confirmarContrasenya by registerViewModel.confirmarContrasenya.observeAsState(initial = "")
        val estadoIcono by registerViewModel.estadoIcono.observeAsState(false)
        val goNext by registerViewModel.goToNext.observeAsState(0)
        val registerEstado by registerViewModel.resultadoRegistro.observeAsState()
        registerViewModel.DialogoRegister(goNext)


        //LOGO
        Image(
            painter = painterResource(id = R.drawable.logo_empresa),
            contentDescription = "Logo de la Empresa",
            modifier = Modifier.constrainAs(logo) {
                bottom.linkTo(margenSuperior)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })


        //TEXTFIELD NOMBRE
        OutlinedTextField(
            value = name,
            onValueChange = {
                registerViewModel.cambioDatos(
                    it,
                    correo,
                    contrasenya,
                    confirmarContrasenya
                )
            },
            label = { Text(text = "Nombre") },
            colors = Colorss(),
            maxLines = 1,
            singleLine = true,
            modifier = Modifier.constrainAs(textFieldNombre) {

                top.linkTo(logo.bottom, margin = 50.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)


            })


        //TEXTFIELD CORREO
        OutlinedTextField(
            value = correo,
            onValueChange = {
                registerViewModel.cambioDatos(
                    name,
                    it,
                    contrasenya,
                    confirmarContrasenya
                )
            },
            label = { Text(text = "Correo electronico") },
            colors = Colorss(),
            maxLines = 1,
            singleLine = true,
            modifier = Modifier.constrainAs(textFieldCorreo) {

                top.linkTo(textFieldNombre.bottom, margin = 10.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)


            })


        //TEXTFIELD CONSTRASENYA
        OutlinedTextField(
            value = contrasenya,
            onValueChange = {
                registerViewModel.cambioDatos(
                    name,
                    correo,
                    it,
                    confirmarContrasenya
                )
            },


            trailingIcon = {
                Icon(
                    imageVector =registerViewModel.elegirIcono(estadoIcono),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.clickable {registerViewModel.iconoPassword(estadoIcono)})
            },
            label = { Text(text = "Constraseña") },
            colors = Colorss(),
            maxLines = 1,
            singleLine = true,
            visualTransformation = registerViewModel.mostrarPassword(estadoIcono),
            modifier = Modifier.constrainAs(textFieldContrasenya) {

                top.linkTo(textFieldCorreo.bottom, margin = 35.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)


            })

        AnimatedVisibility(visible = contrasenya.isNotEmpty(), modifier = Modifier.constrainAs(progressBar){
            top.linkTo(textFieldContrasenya.bottom)
            bottom.linkTo(textFIeldConfirmarContrasenya.top)
            start.linkTo(textFieldContrasenya.start)


        }) {
            LinearProgressIndicator(
                progress = registerViewModel.calcularProgreso(contrasenya),
                trackColor = registerViewModel.mostrarBarraProgressBar(contrasenya),
                color = registerViewModel.colorProgressBar(contrasenya)





            )
            
        }





        //TEXTFIELD CONFIRMAR CONTRASENYA
        OutlinedTextField(
            value = confirmarContrasenya,
            onValueChange = { registerViewModel.cambioDatos(name, correo, contrasenya, it) },
            label = { Text(text = "Confirmar contraseña") },
            colors = Colorss(),
            maxLines = 1,

            singleLine = true,
            visualTransformation = registerViewModel.mostrarPassword(estadoIcono),
            modifier = Modifier.constrainAs(textFIeldConfirmarContrasenya) {

                top.linkTo(textFieldContrasenya.bottom, margin = 15.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)


            },
            trailingIcon = {
                Icon(
                    imageVector =registerViewModel.elegirIcono(estadoIcono),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.clickable {registerViewModel.iconoPassword(estadoIcono)})
            },)
        Text(
            text = "Las contrasenyas no coinciden",
            color = registerViewModel.contrasenyasCoinciden(contrasenya, confirmarContrasenya),
            modifier = Modifier.constrainAs(textoContrasenyasCoinciden) {
                top.linkTo(textFIeldConfirmarContrasenya.bottom, margin = 8.dp)
                start.linkTo(textFieldContrasenya.start)

            })



        Button(
            onClick = {
                registerViewModel.register(correo, contrasenya)
            },
            colors = colorsButton(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 75.dp, end = 75.dp)
                .constrainAs(boton1) {
                    top.linkTo(textFIeldConfirmarContrasenya.bottom, margin = 50.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            enabled = registerViewModel.cambioDatos(name, correo, contrasenya, confirmarContrasenya)
        ) {
            Text(text = "REGISTER")


        }


    }

}


@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    Register(rememberNavController(), RegisterViewModel())

}

@Composable
fun Colorss(): TextFieldColors {
    val colors = OutlinedTextFieldDefaults.colors(
        unfocusedBorderColor = Color.White,
        focusedBorderColor = Color.Gray,
        focusedTextColor = Color.White,
        unfocusedTextColor = Color.White,
        cursorColor = Color.Transparent,

        )
    return colors
}




@Composable
fun colorsButton(): ButtonColors {
    val colors = ButtonDefaults.buttonColors(
        contentColor = colorResource(id = R.color.tikrayColor1),
        containerColor = Color.White,
        disabledContainerColor = Color.Gray
    )
    return colors
}








