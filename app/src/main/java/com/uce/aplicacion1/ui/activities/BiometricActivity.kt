package com.uce.aplicacion1.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import com.uce.aplicacion1.databinding.ActivityBiometricBinding
import com.uce.aplicacion1.ui.entites.DataStoreEntitiy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.Executor
val Context.settingsDataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")


class BiometricActivity : AppCompatActivity() {


    private lateinit var binding: ActivityBiometricBinding

    private lateinit var executor: Executor

    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    private lateinit var biometricManager: BiometricManager


    // @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val splash = installSplashScreen()


        binding = ActivityBiometricBinding.inflate(layoutInflater)
        setContentView(binding.root)



        initListener()
        initVariables()

        Thread.sleep(2000)
        splash.setKeepOnScreenCondition() {
            false
        }
    }

    private fun initVariables() {

        executor = ContextCompat.getMainExecutor(this)



        promptInfo = BiometricPrompt.PromptInfo.Builder().setTitle("My Application")
            .setSubtitle("Ingrese su huella digital").setNegativeButtonText("Cancelar").build()

        biometricPrompt = BiometricPrompt(this,
            executor, object : BiometricPrompt.AuthenticationCallback() {

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    // va el codigo para ingresar a la app

                    val x = Intent(applicationContext, MainActivity::class.java)

                    startActivity(x)
                }

                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                }
            })
    }


    private fun initListener() {
        binding.finger.setOnClickListener() {

            dataStoreSave(DataStoreEntitiy("Dennisse",true))

         Log.d("TAG", dataStoreGet().toString())
            //initBiometric()
        }
    }

    private fun initBiometric() {

        biometricManager =
            BiometricManager.from(this)//clase maestra para acceder a las huellas de l sistema


        val x = biometricManager.canAuthenticate(
            BiometricManager.Authenticators.DEVICE_CREDENTIAL
                    or BiometricManager.Authenticators.BIOMETRIC_STRONG
        )
        when (x) {
            BiometricManager.BIOMETRIC_SUCCESS -> {
                biometricPrompt.authenticate(promptInfo)
            }

            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {}
            BiometricManager.BIOMETRIC_ERROR_UNSUPPORTED -> {}
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                val enrollIntent = Intent(Settings.ACTION_BIOMETRIC_ENROLL).apply {
                    putExtra(
                        Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                        BiometricManager.Authenticators.DEVICE_CREDENTIAL
                                or BiometricManager.Authenticators.BIOMETRIC_STRONG
                    )
                }
                startActivity(enrollIntent)
            }

            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                TODO()
            }

            BiometricManager.BIOMETRIC_ERROR_SECURITY_UPDATE_REQUIRED -> {
                TODO()
            }

            BiometricManager.BIOMETRIC_STATUS_UNKNOWN -> {
                TODO()
            }
        }
    }


    //guardar
    private fun dataStoreSave(user: DataStoreEntitiy) {
        lifecycleScope.launch(Dispatchers.IO) {

            settingsDataStore.edit { prefs ->

                prefs[booleanPreferencesKey("active")] = user.active
                prefs[stringPreferencesKey(name = "user")] = user.name

            }
        }
    }

    //recuperar
    private fun dataStoreGet(): DataStoreEntitiy {

        var ret = DataStoreEntitiy()

        lifecycleScope.launch(Dispatchers.Main) {
            val x = withContext(Dispatchers.IO) {
                settingsDataStore.data.map { prefs ->

                    DataStoreEntitiy(
                        prefs[stringPreferencesKey(name = "user")] ?: "",
                        prefs[booleanPreferencesKey(name = "active")] ?: false
                    )
                }
            }
           x.first()

        }
        return ret
    }
}
