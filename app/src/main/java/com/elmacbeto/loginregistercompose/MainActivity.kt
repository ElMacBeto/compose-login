package com.elmacbeto.loginregistercompose

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.fragment.app.FragmentActivity
import com.elmacbeto.loginregistercompose.ui.screens.AppNavigation
import com.elmacbeto.loginregistercompose.ui.theme.LoginRegisterComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LoginRegisterComposeTheme {
                AppNavigation()
            }
        }
    }
}