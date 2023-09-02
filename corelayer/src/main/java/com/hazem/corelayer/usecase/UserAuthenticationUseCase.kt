package com.hazem.corelayer.usecase

import com.hazem.corelayer.model.ResultData

interface UserAuthenticationUseCase {
    suspend operator fun invoke(): ResultData<Boolean>

}