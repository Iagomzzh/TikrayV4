package iago.tikray.tikrayv4.Login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import iago.tikray.tikrayv4.R
import iago.tikray.tikrayv4.Register.colorsButton
import iago.tikray.tikrayv4.Register.Colorss
import iago.tikray.tikrayv4.Register.RegisterViewModel


@Composable
fun ScreenLogin(navigationController: NavHostController, loginViewModel: LoginViewModel) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.tikrayColor1))
    ) {
        // VARIABLES PARA LA COLOCACION DE CONSTRANT LAYOUT
        val (logo, textFieldCorreo, textFieldPassword, boton, passwordOlvidada) = createRefs()
        val margenSuperior = createGuidelineFromTop(0.25f)

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // VARIABLES PARA EL LOGIN

        val correo: String by loginViewModel.email.observeAsState(initial = "")
        val passwrd: String by loginViewModel.password.observeAsState(initial = "")

        val goNext by loginViewModel.goToNextLogin.observeAsState(0)


        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // FUNCIÓN PARA QUE EL DIALOG ALERT APAREZCA DEPENDIENDO DE SI EL LOGIN HA SIDO SATISFACTORIO O NO

        loginViewModel.DialogoLogin(goNext)


        //IMAGEN LOGO PARTE SUPERIOR
        Image(
            painter = painterResource(id = R.drawable.logo_empresa),
            contentDescription = "Logo de la empresa",
            modifier = Modifier.constrainAs(logo) {
                bottom.linkTo(margenSuperior)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //CAMPO DE TEXTO PARA EL CORREO

        OutlinedTextField(value = correo,
            onValueChange = { loginViewModel.changeDatos(correo = it, contrasenya = passwrd) },
            label = { Text(text = "Correo electronico") },
            colors = Colorss(),
            maxLines = 1,
            singleLine = true,
            modifier = Modifier.constrainAs(textFieldCorreo) {
                top.linkTo(logo.bottom, margin = 100.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //CAMPO DE TEXTO PARA LA CONTRASENYA


        OutlinedTextField(value = passwrd,
            onValueChange = { loginViewModel.changeDatos(correo = correo, contrasenya = it) },
            label = { Text(text = "Contraseña") },
            colors = Colorss(),
            maxLines = 1,
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.constrainAs(textFieldPassword) {
                top.linkTo(textFieldCorreo.bottom, margin = 25.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //CONTRASENYA OLVIDADA BOTON

        Text(
            text = "¿Has olvidado la contraseña?",
            color = Color(48, 57, 244, 255),
            fontSize = 12.sp,
            modifier = Modifier
                .constrainAs(passwordOlvidada) {
                    top.linkTo(textFieldPassword.bottom, margin = 5.dp)
                    end.linkTo(textFieldPassword.end)
                }
                .clickable { navegarPasswdOlvidada(navigationController) })

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // BOTON PARA HACER EL LOGIN

        Button(
            onClick = { loginViewModel.login()},
            colors = colorsButton(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 75.dp, end = 75.dp)
                .constrainAs(boton) {
                    top.linkTo(textFieldPassword.bottom, margin = 60.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            enabled = loginViewModel.changeDatos(correo, passwrd)
        ) {
            Text(text = "LOGIN")


        }

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    }


}


// PREVIEW

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun Preview() {
    ScreenLogin(navigationController = rememberNavController(), LoginViewModel())

}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////