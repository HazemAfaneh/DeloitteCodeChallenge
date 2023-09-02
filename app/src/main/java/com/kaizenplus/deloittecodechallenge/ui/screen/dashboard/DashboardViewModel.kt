package com.kaizenplus.deloittecodechallenge.ui.screen.dashboard

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.hazem.corelayer.model.User
import com.hazem.corelayer.user.LogoutUserUseCase
import com.hazem.corelayer.user.UserUseCase
import com.kaizenplus.deloittecodechallenge.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.cancel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val userUseCase: UserUseCase,
    private val logoutUserUseCase: LogoutUserUseCase,
    @ApplicationContext context: Context
) : BaseViewModel(context) {

    private val _uiState: MutableStateFlow<UiState?> = MutableStateFlow(null)
    val uiState: StateFlow<UiState?> get() = _uiState

    data class UiState(
        val isLoading: Boolean? = null,
        val isLoggedOut: Boolean? = null,
        val userData: User? = null,
        val errorMessage: String? = null
    )

    sealed class UIAction {
        object GetUserInfo : UIAction()
        data class Logout(val name:String) : UIAction()
    }

    fun actionTrigger(action: UIAction) {
        viewModelScope.launch {
            _uiState.emit(UiState(isLoading = true))
            when (action) {
                is UIAction.GetUserInfo -> {
                    getUserInfo()
                }

                is UIAction.Logout -> {
                    logout(action.name)
                }
            }

        }
    }

    private suspend fun getUserInfo() {
        coroutineScope {
            handleResult(userUseCase(), {
                viewModelScope.launch {
                    _uiState.emit(
                        UiState(
                            isLoading = false,
                            userData = it,
                        )
                    )
                }
            }, {
                viewModelScope.launch {
                    cancel()
                    _uiState.emit(
                        UiState(
                            isLoading = false,
                            errorMessage = it
                        )
                    )
                }
            })
        }
    }
    private suspend fun logout(name:String) {
        coroutineScope {
            handleResult(logoutUserUseCase(name), {
                viewModelScope.launch {
                    if(it){
                        _uiState.emit(
                            UiState(
                                isLoading = false,
                                userData = null,
                                isLoggedOut = true
                            )
                        )
                    } else {

                    }
                }
            }, {
                viewModelScope.launch {
                    cancel()
                    _uiState.emit(
                        UiState(
                            isLoading = false,
                            errorMessage = it
                        )
                    )
                }
            })
        }
    }


}