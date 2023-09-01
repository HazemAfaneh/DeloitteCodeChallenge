package com.kaizenplus.deloittecodechallenge.ui.screen.splashscreen

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.hazem.corelayer.usecase.UserAuthenticationUseCase
import com.kaizenplus.deloittecodechallenge.BaseViewModel
import com.kaizenplus.deloittecodechallenge.R
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.cancel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val userAuthenticationUseCase: UserAuthenticationUseCase,
    @ApplicationContext context: Context
) : BaseViewModel(context) {

    private val _uiState: MutableStateFlow<UiState?> = MutableStateFlow(null)
    val uiState: StateFlow<UiState?> get() = _uiState

    data class UiState(
        val isAuthenticated: Boolean? = null,
        val errorMessage: String? = null,
    )

    sealed class UIAction {
        object CheckUserAuthentication : UIAction()
    }

    private suspend fun getUserAuthentication() {
        coroutineScope {
            handleResult(userAuthenticationUseCase(), {
                viewModelScope.launch {
                    _uiState.emit(
                        UiState(
                            isAuthenticated = it
                        )
                    )
                }
            }, {
                viewModelScope.launch {
                    cancel()
                    _uiState.emit(
                        UiState(
                            isAuthenticated = false,
                            errorMessage = it
                        )
                    )
                }
            })
        }
    }

    fun actionTrigger(action: UIAction) {
        viewModelScope.launch {
            when (action) {
                is UIAction.CheckUserAuthentication -> {
                    getUserAuthentication()
                }
            }

        }
    }


}