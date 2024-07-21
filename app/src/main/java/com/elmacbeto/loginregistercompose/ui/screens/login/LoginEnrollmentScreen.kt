package com.elmacbeto.loginregistercompose.ui.screens.login

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import com.elmacbeto.loginregistercompose.R
import com.elmacbeto.loginregistercompose.core.utils.biometrics.BiometricsUtils
import com.elmacbeto.loginregistercompose.ui.theme.Blue1
import com.elmacbeto.loginregistercompose.ui.theme.Red1
import com.elmacbeto.loginregistercompose.ui.theme.Style

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginEnrollmentScreen(navController: NavController? = null) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = { paddingValues ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                LoginEnrollmentContent(navController = navController)
            }
        }
    )
}

@Composable
fun LoginEnrollmentContent(
    name: String = "Humberto",
    navController: NavController?
) {
    val context = LocalContext.current
    var password by remember { mutableStateOf("") }


    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (imgTop, tTitle, btnFingerprint, btnLoginWithPass, tbForgotPassword) = createRefs()
        val (tOtherLogin, otherLogins) = createRefs()

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
            text = name,
            style = Style.title2,
            modifier = Modifier
                .padding(horizontal = 22.dp)
                .constrainAs(tTitle) {
                   bottom.linkTo(btnFingerprint.top, 22.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        Icon(
            painterResource(id = R.drawable.ic_fingerprint),
            contentDescription = "leading Icon",
            tint = Blue1,
            modifier = Modifier
                .size(88.dp)
                .border(2.dp, Blue1, CircleShape)
                .clickable {
                    BiometricsUtils.showBiometricPrompt(context as FragmentActivity) { isAuth ->
                        if (isAuth) {
                            Toast.makeText(context, "autenticacion exitosa", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            Toast.makeText(context, "autenticacion fallida", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
                .constrainAs(btnFingerprint) {
                    top.linkTo(imgTop.bottom)
                    bottom.linkTo(tOtherLogin.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        Text(
            text = "Ingresar con contrasena",
            style = Style.title5,
            modifier = Modifier
                .padding(horizontal = 22.dp)
                .clickable {

                }
                .constrainAs(btnLoginWithPass) {
                    top.linkTo(btnFingerprint.bottom, 32.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        Text(
            text = "Iniciar sesion con",
            style = Style.title4,
            modifier = Modifier
                .constrainAs(tOtherLogin) {
                    bottom.linkTo(otherLogins.top, 12.dp)
                    start.linkTo(parent.start, 22.dp)
                }
        )

        Row(modifier = Modifier
            .fillMaxWidth()
            .constrainAs(otherLogins) {
                bottom.linkTo(tbForgotPassword.top, 42.dp)
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

        Text(
            text = "Olvidades tu contrasena?",
            style = Style.title3,
            modifier = Modifier
                .padding(horizontal = 22.dp)
                .alpha(0f)
                .clickable {
                    Toast
                        .makeText(context, "olvido contrasena", Toast.LENGTH_SHORT)
                        .show()
                }
                .constrainAs(tbForgotPassword) {
                    bottom.linkTo(parent.bottom, 42.dp)
                    end.linkTo(parent.end, 12.dp)
                }
        )
    }
}

@Preview
@Composable
fun PreviewLoginEnrollmentScreen() {
    Surface {
        LoginEnrollmentScreen()
    }
}