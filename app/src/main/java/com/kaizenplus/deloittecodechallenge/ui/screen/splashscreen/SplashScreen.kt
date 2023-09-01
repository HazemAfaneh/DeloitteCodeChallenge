package com.kaizenplus.deloittecodechallenge.ui.screen.splashscreen

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.kaizenplus.deloittecodechallenge.ui.screen.dashboard.DashboardActivity
import com.kaizenplus.deloittecodechallenge.ui.screen.login.MainActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreen : ComponentActivity() {
    private lateinit var splashScreen: SplashScreen

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition { true }
        val context = this
        lifecycleScope.launch {
            delay(200)
            if (false) {
                startActivity(Intent(context, DashboardActivity::class.java))
            } else {
                startActivity(Intent(context, MainActivity::class.java))
            }
            finish()
        }
    }
}
