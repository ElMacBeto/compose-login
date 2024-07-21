package com.elmacbeto.loginregistercompose.core.utils.biometrics

import androidx.biometric.BiometricPrompt
import androidx.fragment.app.FragmentActivity
import java.util.concurrent.Executors

object BiometricsUtils {
    private fun createBiometricPrompt(
        fragment: FragmentActivity,
        processSuccess: (Boolean) -> Unit
    ): BiometricPrompt {
        return BiometricPrompt(
            fragment,
            Executors.newSingleThreadExecutor(),
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    fragment.runOnUiThread {
                        processSuccess(false)
                    }
                    super.onAuthenticationError(errorCode, errString)
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    fragment.runOnUiThread {
                        processSuccess(true)
                    }
                    super.onAuthenticationSucceeded(result)
                }

                override fun onAuthenticationFailed() {
                    fragment.runOnUiThread {
                        processSuccess(false)
                    }
                    super.onAuthenticationFailed()
                }
            }
        )
    }


    fun showBiometricPrompt(
        fragment: FragmentActivity,
        processSuccess: (Boolean) -> Unit
    ) {
        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Autenticacion")
            .setSubtitle("Autenticacion usando fingerprint")
            .setNegativeButtonText("Cancelar")
            .build()
        createBiometricPrompt(fragment, processSuccess).authenticate(promptInfo)
    }
}