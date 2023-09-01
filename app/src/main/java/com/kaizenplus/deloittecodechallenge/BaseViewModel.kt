package com.kaizenplus.deloittecodechallenge

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hazem.corelayer.model.ErrorEntity
import com.hazem.corelayer.model.ResultData
import dagger.hilt.android.qualifiers.ApplicationContext
//import com.hazem.coredeloittecodechallenge.core.model.ErrorEntity
//import com.hazem.coredeloittecodechallenge.core.model.ResultData
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


open class BaseViewModel @Inject constructor(@ApplicationContext val context:Context) : ViewModel() {
//    private val generalUiState: MutableSharedFlow<ErrorEntity> = MutableSharedFlow()


    fun <T> handleResult(
        result: ResultData<T>,
        onSuccess: (T) -> Unit,
        onError: (String) -> Unit
    ) {

        when (result) {
            is ResultData.Error -> {
                val errorMessage =
                    when (result.data) {
                        is ErrorEntity.ApiError -> {
                            (result.data as ErrorEntity.ApiError).message
                                .firstOrNull { it.isNotBlank() }
                                ?.takeIf { it.isNotEmpty() }
                                ?: context.getString(R.string.unknown_error)
                        }

                        is ErrorEntity.InternalError -> {
                            (result.data as ErrorEntity.InternalError).message.ifBlank {
                                context.getString(
                                    R.string.unknown_error
                                )
                            }
                        }

                        is ErrorEntity.NoConnection -> {
                            context.getString(R.string.no_connection)
                        }

                        else -> {
                            context.getString(R.string.unknown_error)
                        }
                    }
                onError(errorMessage)
            }

            is ResultData.Success -> onSuccess(result.data)
        }

    }
}