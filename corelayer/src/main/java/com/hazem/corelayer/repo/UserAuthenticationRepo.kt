package com.hazem.corelayer.repo

import com.hazem.corelayer.model.ResultData

interface UserAuthenticationRepo {
    suspend fun isAuthenticated(): ResultData<Boolean>

}