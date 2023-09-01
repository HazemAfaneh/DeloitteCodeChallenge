package com.hazem.corelayer.register

import com.hazem.corelayer.model.ResultData
import com.hazem.corelayer.model.User

interface RegisterUserCase {
    suspend operator fun invoke(user: User): ResultData<Boolean>
}