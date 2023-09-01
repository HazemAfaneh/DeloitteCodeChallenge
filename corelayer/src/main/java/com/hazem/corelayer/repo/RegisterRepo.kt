package com.hazem.corelayer.repo

import com.hazem.corelayer.model.ResultData
import com.hazem.corelayer.model.User

interface RegisterRepo {
    suspend fun register(user: User): ResultData<Boolean>
    suspend fun saveUserToCache(user: User): ResultData<Boolean>
}