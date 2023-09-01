package com.hazem.corelayer.repo

import com.hazem.corelayer.model.ResultData
import com.hazem.corelayer.model.User

interface LoginRepo {
    suspend fun login(user:User): ResultData<Boolean>
}