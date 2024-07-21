package com.elmacbeto.loginregistercompose.core.utils

object Tools {

    private val nameRegex = "^[A-Za-z]+(?:[-'\\s][A-Za-z]+)*$".toRegex()
    private val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()

    fun isNameValid(text: String): Boolean = nameRegex.matches(text)

    fun isEmailValid(text: String): Boolean = emailRegex.matches(text)

    fun isPasswordValid(text: String): Boolean = text.length in 4..8

}