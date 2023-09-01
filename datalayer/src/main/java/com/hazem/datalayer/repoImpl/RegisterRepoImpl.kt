package com.hazem.datalayer.repoImpl

import com.hazem.corelayer.model.ResultData
import com.hazem.corelayer.model.User
import com.hazem.corelayer.repo.RegisterRepo
import com.hazem.datalayer.cache.dao.UserDaoImpl
import kotlinx.coroutines.delay
import javax.inject.Inject

class RegisterRepoImpl @Inject constructor(private val daoImpl: UserDaoImpl) : RegisterRepo {
    override suspend fun register(user: User): ResultData<Boolean> {
        delay(2000) // register api call
        return ResultData.Success(true)
    }

    override suspend fun saveUserToCache(user: User): ResultData<Boolean> {
        return daoImpl.insert(user)
    }
}