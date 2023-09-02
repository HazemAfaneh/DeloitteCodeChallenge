package com.hazem.datalayer.repoImpl

import com.hazem.corelayer.model.ResultData
import com.hazem.corelayer.model.User
import com.hazem.corelayer.model.doIfSuccess
import com.hazem.corelayer.repo.LoginRepo
import com.hazem.datalayer.cache.dao.UserDao
import kotlinx.coroutines.delay
import javax.inject.Inject

class LoginRepoImpl @Inject constructor(
    private val authenticator: Authenticator,
    private val dao: UserDao
) : LoginRepo {
    override suspend fun login(user: User): ResultData<Boolean> {
        delay(2000)//api login simulation
        user.apply {
            fullName = "Hazem Amjad Afaneh --Dummy login data"
            nationalId = 11111111
            phoneNumber = 789301961
        }
        return dao.insert(user).doIfSuccess {
            authenticator.login(user)
            true
        }
//        return ResultData.Success(true)
    }

}