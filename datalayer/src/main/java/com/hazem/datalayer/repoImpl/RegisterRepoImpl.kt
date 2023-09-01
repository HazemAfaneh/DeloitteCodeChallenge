package com.hazem.datalayer.repoImpl

import com.hazem.corelayer.model.ResultData
import com.hazem.corelayer.model.User
import com.hazem.corelayer.repo.RegisterRepo
import kotlinx.coroutines.delay

class RegisterRepoImpl : RegisterRepo {
    override suspend fun register(user: User): ResultData<Boolean> {
        delay(2000)
        return ResultData.Success(true)
    }
}