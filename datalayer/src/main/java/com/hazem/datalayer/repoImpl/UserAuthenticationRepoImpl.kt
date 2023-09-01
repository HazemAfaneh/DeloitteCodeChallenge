package com.hazem.datalayer.repoImpl

import com.hazem.corelayer.model.ResultData
import com.hazem.corelayer.repo.UserAuthenticationRepo
import javax.inject.Inject


class UserAuthenticationRepoImpl @Inject constructor() : UserAuthenticationRepo {
    override suspend fun isAuthenticated(): ResultData<Boolean> {

        return ResultData.Success(false)
    }
}