package com.hazem.corelayer.user

import com.hazem.corelayer.model.ResultData
import com.hazem.corelayer.model.User
import com.hazem.corelayer.repo.UserRepo
import javax.inject.Inject

class UserUseCaseImpl
@Inject constructor(private val repo: UserRepo) :UserUseCase {
    override suspend fun invoke(): ResultData<User?> {
        return repo.getUserInfo()
    }
}