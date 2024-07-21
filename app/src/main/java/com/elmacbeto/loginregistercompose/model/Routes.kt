package com.elmacbeto.loginregistercompose.model

import androidx.navigation.NamedNavArgument

sealed class Routes(
    val route: String,
    val navArguments: List<NamedNavArgument> = emptyList()
) {
    object Login : Routes("login")

    object Register : Routes("register")

    object LoginEnrollment : Routes("loginEnrollment")

}