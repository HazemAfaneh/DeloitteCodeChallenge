package com.hazem.corelayer.login

import com.hazem.corelayer.model.ResultData
import com.hazem.corelayer.model.User
import com.hazem.corelayer.repo.LoginRepo
import javax.inject.Inject

class LoginUseCaseImpl @Inject constructor(private val loginRepo: LoginRepo) : LoginUseCase {
    override suspend fun invoke(user: User): ResultData<Boolean> {
        return loginRepo.login(user)
    }
}