package com.kaizenplus.deloittecodechallenge.ui.screen.splashscreen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.kaizenplus.deloittecodechallenge.ui.screen.dashboard.DashboardActivity
import com.kaizenplus.deloittecodechallenge.ui.screen.login.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashScreen : ComponentActivity() {
    private lateinit var splashScreen: SplashScreen
    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition { true }
        collectUIState(this)
        viewModel.actionTrigger(action = SplashViewModel.UIAction.CheckUserAuthentication)
    }
    private fun collectUIState(context: Context) {
        lifecycleScope.launch {
            viewModel.uiState.collect {
                it?.let { result ->
                    if (result.isAuthenticated == true) {
                        startActivity(Intent(context, DashboardActivity::class.java))
                        finish()
                    } else {
                        result.errorMessage?.let { message ->
                            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                        } ?: run  {
                            startActivity(Intent(context, MainActivity::class.java))
                            finish()
                        }
                    }
                }


            }

        }
    }
}
