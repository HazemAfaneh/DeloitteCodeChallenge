package com.hazem.datalayer.repoImpl

import com.hazem.corelayer.model.ErrorEntity
import com.hazem.corelayer.model.ResultData
import com.hazem.corelayer.model.User
import com.hazem.corelayer.repo.LoginRepo
import kotlinx.coroutines.delay
import javax.inject.Inject

class LoginRepoImpl @Inject constructor(private val authenticator: Authenticator) : LoginRepo {
    override suspend fun login(user: User): ResultData<Boolean> {
        delay(2000)
          authenticator.login(user)
        return ResultData.Error(ErrorEntity.Unknown)
    }

}