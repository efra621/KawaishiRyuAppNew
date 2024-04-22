package app.gratum.kawaishiryuappnew.ui.dialog

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import app.gratum.kawaishiryuappnew.ui.activity.MainMenuHostActivity
import app.gratum.kawaishiryuappnew.core.ViewModelState
import app.gratum.kawaishiryuappnew.databinding.ActivitySplashScreenBinding
import app.gratum.kawaishiryuappnew.ui.activity.MainActivity
import app.gratum.kawaishiryuappnew.ui.viewmodel.LoginScreenViewModel
import app.gratum.kawaishiryuappnew.util.SnackbarUtils
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    private val viewModel by viewModels<LoginScreenViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            delay(3000L)
            checkUserLoggedIn()
        }
    }

    private suspend fun checkUserLoggedIn() {
        viewModel.userLogged()

        viewModel.loggedInUser.collect { result ->
            when (result) {
                is ViewModelState.Loading2 -> {
                    Log.d("???", "SplashScreenAct: Loading user... : ")
                }

                is ViewModelState.Success2 -> {
                    Log.d("???", "SplashScreenAct: User found... : ${result.message}")
                    //CREATE A DATASTORE
                    navigateToNextActivity(true)
                }

                is ViewModelState.Empty -> {
                    Log.d("???", "SplashScreenAct: User not logged in")
                    navigateToNextActivity(false)
                }

                is ViewModelState.Error2 -> {
                    Log.d("???", "SplashScreenAct: Error: ${result}")
                    navigateToNextActivity(false)
                }

                else -> {
                    Log.d("???", "SplashScreenAct: Unknown Error")
                    navigateToNextActivity(false)
                }
            }
        }
    }

    private fun navigateToNextActivity(isLoggedIn: Boolean) {
        if (isLoggedIn) {
            showWelcomeSnackbar(binding.root)
            navigateToMainMenu()
        } else {
            navigateToMainActivity()
        }
    }

    private fun navigateToMainMenu() {
        startActivity(Intent(this@SplashScreenActivity, MainMenuHostActivity::class.java))
        finish()
    }

    private fun navigateToMainActivity() {
        startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
        finish()
    }

    private fun showWelcomeSnackbar(view: View) {
        SnackbarUtils.showCustomSnackbar(view, layoutInflater, 1, "Bienvenido de nuevo")
    }
}







