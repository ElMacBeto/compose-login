package com.elmacbeto.loginregistercompose.ui.screens

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.elmacbeto.loginregistercompose.model.Routes
import com.elmacbeto.loginregistercompose.ui.screens.login.LoginEnrollmentScreen
import com.elmacbeto.loginregistercompose.ui.screens.login.LoginScreen
import com.elmacbeto.loginregistercompose.ui.screens.register.RegisterScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Login.route) {
        composable(
            route = Routes.Login.route
        ) {
            LoginScreen(navController)
        }
        composable(
            route = Routes.Register.route,
        ) {
            RegisterScreen(navController)
        }
        composable(
            route = Routes.LoginEnrollment.route,
        ) {
            LoginEnrollmentScreen(navController)
        }
    }
}
