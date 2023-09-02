package com.hazem.corelayer.user

import com.hazem.corelayer.model.ResultData

interface LogoutUserUseCase {
    suspend operator fun invoke(name:String): ResultData<Boolean>
}