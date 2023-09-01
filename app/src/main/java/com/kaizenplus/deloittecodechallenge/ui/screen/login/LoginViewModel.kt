package com.kaizenplus.deloittecodechallenge.ui.screen.login

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.hazem.corelayer.login.LoginUseCase
import com.hazem.corelayer.model.ErrorEntity
import com.hazem.corelayer.model.User
import com.hazem.corelayer.register.RegisterUserCase
import com.hazem.corelayer.usecase.UserAuthenticationUseCase
import com.kaizenplus.deloittecodechallenge.BaseViewModel
import com.kaizenplus.deloittecodechallenge.ui.screen.splashscreen.SplashViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.cancel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val registerUserCase: RegisterUserCase,
    @ApplicationContext context: Context
) : BaseViewModel(context) {
    //    private val _uiState: MutableSharedFlow<UiState?> = MutableSharedFlow()
//    val uiState: MutableSharedFlow<UiState?> get() = _uiState
    private val _uiState: MutableStateFlow<UiState?> = MutableStateFlow(null)
    val uiState: StateFlow<UiState?> get() = _uiState

    data class UiState(
        val isLoading: Boolean? = null,
        val isLoggedIn: Boolean? = null,
        val loginErrorMessage: String? = null,
        val registerErrorMessage: String? = null,
    )

    sealed class UIAction {
        data class Login(val user: User) : UIAction()
        data class Register(val user: User) : UIAction()
    }

    private suspend fun login(user: User) {
        coroutineScope {
            handleResult(loginUseCase(user), {
                viewModelScope.launch {
                    _uiState.emit(
                        UiState(
                            isLoading = false,
                            isLoggedIn = it,
                        )
                    )
                }
            }, {
                viewModelScope.launch {
                    cancel()
                    _uiState.emit(
                        UiState(
                            isLoading = false,
                            isLoggedIn = false,
                            loginErrorMessage = it
                        )
                    )
                }
            })
        }


    }

    private suspend fun register(user: User) {
        coroutineScope {
            handleResult(registerUserCase(user), {
//                actionTrigger(UIAction.Login(user))
                viewModelScope.launch {
                    _uiState.emit(
                        UiState(
                            isLoading = false,
                            isLoggedIn = it,
                        )
                    )
                }
            }, {
                viewModelScope.launch {
                    cancel()
                    _uiState.emit(
                        UiState(
                            isLoading = false,
                            isLoggedIn = false,
                            registerErrorMessage = it
                        )
                    )
                }
            })
        }


    }

    fun actionTrigger(action: UIAction) {
        viewModelScope.launch {
            _uiState.emit(UiState(isLoading = true))
            when (action) {
                is UIAction.Login -> {
                    login(action.user)
                }

                is UIAction.Register -> {
                    register(action.user)
                }
            }

        }
    }
}