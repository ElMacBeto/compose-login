package com.elmacbeto.loginregistercompose.ui.screens.login

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.elmacbeto.loginregistercompose.R
import com.elmacbeto.loginregistercompose.core.utils.Tools.isEmailValid
import com.elmacbeto.loginregistercompose.core.utils.Tools.isPasswordValid
import com.elmacbeto.loginregistercompose.model.Routes
import com.elmacbeto.loginregistercompose.ui.screens.register.CustomOutlineTextField
import com.elmacbeto.loginregistercompose.ui.theme.Blue1
import com.elmacbeto.loginregistercompose.ui.theme.Red1
import com.elmacbeto.loginregistercompose.ui.theme.Style

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(navController: NavController? = null) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = { paddingValues ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                HomeContent(navController)
            }
        }
    )
}

@Composable
fun HomeContent(navController: NavController?) {
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isEmailError by remember { mutableStateOf(false) }
    var isPasswordError by remember { mutableStateOf(false) }


    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (imgTop, tTitle, tfEmail, tfPassword, tbForgotPassword) = createRefs()
        val (btnLogin, tOtherLogin, otherLogins, btnRegister) = createRefs()

        Image(
            painter = painterResource(id = R.drawable.img_login_top),
            contentDescription = "background top",
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(imgTop) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        Text(
            text = "Iniciar Sesion",
            style = Style.title1,
            modifier = Modifier
                .padding(horizontal = 22.dp)
                .constrainAs(tTitle) {
                    top.linkTo(imgTop.bottom, 12.dp)
                    start.linkTo(parent.start)
                }
        )

        CustomOutlineTextField(
            text = email,
            label = "Correo",
            icon = Icons.Default.Email,
            action = { email = it },
            isError = isEmailError,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 22.dp)
                .onFocusChanged { focusState ->
                    if (!focusState.isFocused && email.isNotEmpty())
                        isEmailError = !isEmailValid(email)
                }
                .constrainAs(tfEmail) {
                    top.linkTo(tTitle.bottom, 12.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        CustomOutlineTextField(
            text = password,
            label = "Contrasena",
            icon = Icons.Default.Lock,
            action = { password = it },
            isError = isPasswordError,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 22.dp)
                .onFocusChanged { focusState ->
                    if (!focusState.isFocused && password.isNotEmpty())
                        isPasswordError = !isPasswordValid(password)
                }
                .constrainAs(tfPassword) {
                    top.linkTo(tfEmail.bottom, 12.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        Text(
            text = "Olvidades tu contrasena?",
            style = Style.title3,
            modifier = Modifier
                .padding(horizontal = 22.dp)
                .clickable {
                    Toast
                        .makeText(context, "olvido contrasena", Toast.LENGTH_SHORT)
                        .show()
                }
                .constrainAs(tbForgotPassword) {
                    top.linkTo(tfPassword.bottom, 12.dp)
                    end.linkTo(parent.end, 12.dp)
                }
        )

        Button(
            onClick = {
                Toast
                    .makeText(context, "Iniciar sesion", Toast.LENGTH_SHORT)
                    .show()
            },
            colors = ButtonDefaults.buttonColors(containerColor = Blue1),
            shape = RoundedCornerShape(25.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 22.dp)
                .constrainAs(btnLogin) {
                    top.linkTo(tbForgotPassword.bottom, 22.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }) {
            Text(text = "Iniciar sesion")
        }

        Text(
            text = "Iniciar sesion con",
            style = Style.title4,
            modifier = Modifier
                .constrainAs(tOtherLogin) {
                    top.linkTo(btnLogin.bottom, 32.dp)
                    start.linkTo(parent.start, 22.dp)
                }
        )

        Row(modifier = Modifier
            .fillMaxWidth()
            .constrainAs(otherLogins) {
                top.linkTo(tOtherLogin.bottom, 12.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }) {
            Button(
                onClick = {
                    Toast
                        .makeText(context, "Iniciar facebook", Toast.LENGTH_SHORT)
                        .show()
                },
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Blue1),
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 22.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_facebook),
                        contentDescription = "Icono de facebook",
                        tint = Color.White
                    )
                    Text(text = "Facebook")
                }
            }

            Button(
                onClick = {
                    Toast
                        .makeText(context, "Iniciar con google", Toast.LENGTH_SHORT)
                        .show()
                },
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Red1),
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 22.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_google),
                        contentDescription = "Icono de facebook",
                        tint = Color.White
                    )
                    Text(text = "Google")
                }
            }
        }

        OutlinedButton(
            onClick = {
                navController?.navigate(Routes.Register.route)
            },
            border = BorderStroke(2.dp, Blue1),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 22.dp)
                .constrainAs(btnRegister) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom, 32.dp)
                }
        ) {
            Text(text = "Registrarse")
        }
    }
}

@Preview
@Composable
fun PreviewHomeScreen() {
    Surface {
        LoginScreen()
    }
}