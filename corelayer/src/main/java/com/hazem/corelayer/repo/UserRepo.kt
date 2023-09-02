package com.hazem.corelayer.repo

import com.hazem.corelayer.model.ResultData
import com.hazem.corelayer.model.User

interface UserRepo {

    suspend fun getUserInfo(): ResultData<User?>
    suspend fun logout(name:String): ResultData<Boolean>
}