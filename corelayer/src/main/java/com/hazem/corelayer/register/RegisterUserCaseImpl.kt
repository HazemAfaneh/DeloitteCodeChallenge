package com.hazem.corelayer.register

import com.hazem.corelayer.model.ErrorEntity
import com.hazem.corelayer.model.ResultData
import com.hazem.corelayer.model.User
import com.hazem.corelayer.model.doIfSuccess
import com.hazem.corelayer.repo.RegisterRepo
import kotlinx.coroutines.delay
import javax.inject.Inject

class RegisterUserCaseImpl @Inject constructor(private val repo: RegisterRepo) : RegisterUserCase {
    override suspend fun invoke(user: User): ResultData<Boolean> {
        return repo.register(user).doIfSuccess {
            saveUserToCache(user) is ResultData.Success
        }
    }

    private suspend fun saveUserToCache(user: User): ResultData<Boolean> { //cache dao
        delay(1000)
//        return ResultData.Error(ErrorEntity.NoConnection)
        return ResultData.Success(true)
    }
}