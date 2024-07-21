package com.elmacbeto.loginregistercompose.ui.screens.register

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import com.elmacbeto.loginregistercompose.R
import com.elmacbeto.loginregistercompose.core.utils.Tools
import com.elmacbeto.loginregistercompose.core.utils.biometrics.BiometricsUtils.showBiometricPrompt
import com.elmacbeto.loginregistercompose.model.Routes
import com.elmacbeto.loginregistercompose.ui.theme.Blue1
import com.elmacbeto.loginregistercompose.ui.theme.Red1
import com.elmacbeto.loginregistercompose.ui.theme.Style


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RegisterScreen(navController: NavController? = null) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = { paddingValues ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                RegisterContent(navController)
            }
        }
    )
}

@Composable
fun RegisterContent(navController: NavController?) {
    val context = LocalContext.current

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isChecked by remember { mutableStateOf(false) }
    var isNameError by remember { mutableStateOf(false) }
    var isEmailError by remember { mutableStateOf(false) }
    var isPasswordError by remember { mutableStateOf(false) }
    var isCheckFingerprint by remember { mutableStateOf(false) }

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (imgTop, btnBack, tTitle, tfEmail,tfName, tfPassword, tbTermsAndConditions) = createRefs()
        val (btnLogin, cbAgree, tFingerprint,swFingerprint, btnRegister) = createRefs()

        Image(
            painter = painterResource(id = R.drawable.img_login_top),
            contentDescription = "background top",
            modifier = Modifier
                .fillMaxWidth()
                .zIndex(1f)
                .constrainAs(imgTop) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        IconButton(
            onClick = { navController?.navigate(Routes.Login.route)},
            modifier = Modifier
                .width(58.dp)
                .height(58.dp)
                .zIndex(10f)
                .constrainAs(btnBack) {
                    top.linkTo(imgTop.top)
                    bottom.linkTo(imgTop.bottom)
                    start.linkTo(parent.start, 22.dp)
                }
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "back",
                tint = Color.White
            )
        }

        Text(
            text = "Crear\nNueva cuenta",
            style = Style.title1,
            modifier = Modifier
                .padding(horizontal = 22.dp)
                .constrainAs(tTitle) {
                    top.linkTo(imgTop.bottom, 12.dp)
                    start.linkTo(parent.start)
                }
        )
        CustomOutlineTextField(
            text = name,
            label = "Nombre",
            icon = Icons.Default.AccountCircle,
            isError = isNameError,
            action = { name = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 22.dp)
                .onFocusChanged { focusState ->
                    if (!focusState.isFocused && name.isNotEmpty())
                        isNameError = !Tools.isEmailValid(name)
                }
                .constrainAs(tfName) {
                    top.linkTo(tTitle.bottom, 12.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
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
                        isEmailError = !Tools.isEmailValid(email)
                }
                .constrainAs(tfEmail) {
                    top.linkTo(tfName.bottom, 12.dp)
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
                        isPasswordError = !Tools.isPasswordValid(password)
                }
                .constrainAs(tfPassword) {
                    top.linkTo(tfEmail.bottom, 12.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

       Switch(
           checked = isCheckFingerprint,
           onCheckedChange = {
               isCheckFingerprint = it
               if (it) {
                   showBiometricPrompt(context as FragmentActivity){ isAuth ->
                       if (isAuth){
                           Toast.makeText(context, "autenticacion exitosa", Toast.LENGTH_SHORT).show()
                       }else{
                           Toast.makeText(context, "autenticacion fallida", Toast.LENGTH_SHORT).show()
                           isCheckFingerprint = false
                       }
                   }
               }
           },
           modifier = Modifier
           .constrainAs(swFingerprint) {
               top.linkTo(tfPassword.bottom, 12.dp)
               end.linkTo(parent.end, 22.dp)
           }
       )

        Text(
            text = "Huella digital",
            modifier = Modifier
                .constrainAs(tFingerprint) {
                    top.linkTo(swFingerprint.top)
                    bottom.linkTo(swFingerprint.bottom)
                    end.linkTo(swFingerprint.start, 12.dp)
                }
        )

        Checkbox(
            checked = isChecked,
            onCheckedChange = { isChecked = it },
            modifier = Modifier
                .constrainAs(cbAgree) {
                    top.linkTo(tFingerprint.bottom, 12.dp)
                    start.linkTo(parent.start, 12.dp)
                }
        )

        val text = buildAnnotatedString {
            withStyle(style = SpanStyle(color = Blue1)) {
                append("Acepto los")
            }
            withStyle(style = SpanStyle(color = Red1)) {
                append(" terminos y condiciones")
            }
        }

        Text(
            text = text,
            style = Style.title3,
            modifier = Modifier
                .clickable {
                    Toast
                        .makeText(context, "terminos y condiciones", Toast.LENGTH_SHORT)
                        .show()
                }
                .constrainAs(tbTermsAndConditions) {
                    top.linkTo(cbAgree.top)
                    bottom.linkTo(cbAgree.bottom)
                    start.linkTo(cbAgree.end)
                }
        )

        Button(
            onClick = {
                navController?.navigate(Routes.LoginEnrollment.route)
            },
            colors = ButtonDefaults.buttonColors(containerColor = Blue1),
            shape = RoundedCornerShape(25.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 22.dp)
                .constrainAs(btnLogin) {
                    top.linkTo(cbAgree.bottom, 22.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            Text(text = "Crear cuenta")
        }

        OutlinedButton(
            onClick = {
                navController?.navigate(Routes.Login.route)
            },
            border = BorderStroke(2.dp, Blue1),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 22.dp)
                .constrainAs(btnRegister) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom, 32.dp)
                }) {
            Text(
                text = "Ya tienes cuenta? Iniciar sesion",
                style = Style.title4
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomOutlineTextField(
    text: String,
    label: String,
    icon: ImageVector,
    modifier: Modifier,
    isError: Boolean,
    action: (String) -> Unit
) {
    OutlinedTextField(
        value = text, onValueChange = {
            action.invoke(it)
        },
        label = {
            Text(text = label, color = Color.Black)
        },
        shape = RoundedCornerShape(25.dp),
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = "leading Icon",
                tint = Blue1
            )
        },
        maxLines = 1,
        isError = isError,
        trailingIcon = {
            if (isError) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "Error",
                    tint = Color.Red
                )
            }
        },
        modifier = modifier
    )
}

@Preview
@Composable
fun PreviewHomeScreen() {
    Surface {
        RegisterScreen()
    }
}