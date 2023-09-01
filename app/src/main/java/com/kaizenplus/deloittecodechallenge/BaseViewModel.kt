package com.kaizenplus.deloittecodechallenge

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

open class BaseViewModel @Inject constructor(): ViewModel() {

//    val generalUiState: MutableSharedFlow<ErrorEntity> = MutableSharedFlow()
//
//    fun <T> handleResult(
//        result: ResultData<T>,
//        onSuccess: (T) -> Unit,
//        onApiError: (ErrorEntity.ApiError) -> Unit
//    ) {
//
//        when (result) {
//            is ResultData.Error -> {
//                when (result.data) {
//                    is ErrorEntity.ApiError -> {
//                        onApiError(result.data as ErrorEntity.ApiError)
//                    }
//                    else -> {
//                        viewModelScope.launch {
//                            generalUiState.emit(result.data)
//                        }
//                    }
//                }
//            }
//            is ResultData.Success -> onSuccess(result.data)
//        }
//
//    }
}