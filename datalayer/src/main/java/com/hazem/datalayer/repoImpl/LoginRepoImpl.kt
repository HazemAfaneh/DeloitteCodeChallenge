package com.hazem.datalayer.repoImpl

import com.hazem.corelayer.model.ErrorEntity
import com.hazem.corelayer.model.ResultData
import com.hazem.corelayer.model.User
import com.hazem.corelayer.repo.LoginRepo
import kotlinx.coroutines.delay

class LoginRepoImpl : LoginRepo {
    override suspend fun login(user: User): ResultData<Boolean> {
        delay(2000)

        return ResultData.Error(ErrorEntity.NoConnection)
//        return ResultData.Success(
//           true
//        )
    }
}