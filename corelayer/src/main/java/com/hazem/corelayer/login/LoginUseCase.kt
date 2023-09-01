package com.hazem.corelayer.login

import com.hazem.corelayer.model.ResultData
import com.hazem.corelayer.model.User

interface LoginUseCase {
    suspend operator fun invoke(user:User): ResultData<Boolean>
}