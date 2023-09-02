package com.hazem.datalayer.repoImpl

import com.hazem.corelayer.model.ResultData
import com.hazem.corelayer.model.User
import com.hazem.corelayer.model.doIfSuccess
import com.hazem.corelayer.repo.UserRepo
import com.hazem.datalayer.cache.dao.UserDaoImpl
import javax.inject.Inject

class UserRepoImpl @Inject constructor(
    private val daoImpl: UserDaoImpl,
    private val authenticator: Authenticator
) : UserRepo {

    override suspend fun getUserInfo(): ResultData<User?> {
        return daoImpl.getUser()
    }

    override suspend fun logout(name:String): ResultData<Boolean> {
        return daoImpl.deleteUser().doIfSuccess {
            authenticator.removeAccount(name)
            true
        };
    }
}