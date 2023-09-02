package com.hazem.datalayer.repoImpl

import com.hazem.corelayer.model.ResultData
import com.hazem.corelayer.repo.UserAuthenticationRepo
import com.hazem.datalayer.cache.dao.UserDaoImpl
import javax.inject.Inject


class UserAuthenticationAuthenticationRepoImpl @Inject constructor(
    private val daoImpl: UserDaoImpl
) : UserAuthenticationRepo {
    override suspend fun isAuthenticated(): ResultData<Boolean> {
        val result = daoImpl.getUser()
        return ResultData.Success(
            result is ResultData.Success && result.data != null
        )
    }

}