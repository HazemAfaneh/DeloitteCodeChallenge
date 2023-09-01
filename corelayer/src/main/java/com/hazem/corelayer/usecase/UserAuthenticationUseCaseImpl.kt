package com.hazem.corelayer.usecase

import com.hazem.corelayer.model.ResultData
import com.hazem.corelayer.repo.UserAuthenticationRepo
import javax.inject.Inject

class UserAuthenticationUseCaseImpl
@Inject constructor(private val repo: UserAuthenticationRepo) :
    UserAuthenticationUseCase {
        override suspend fun invoke(): ResultData<Boolean> {
            return repo.isAuthenticated()

        }
}