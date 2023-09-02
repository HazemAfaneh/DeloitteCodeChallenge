package com.hazem.corelayer.user

import com.hazem.corelayer.model.ResultData
import com.hazem.corelayer.repo.UserRepo
import kotlinx.coroutines.delay
import javax.inject.Inject

class LogoutUserUseCaseImpl
@Inject constructor(private val repo: UserRepo) :LogoutUserUseCase {
    override suspend operator fun invoke(name:String): ResultData<Boolean> {
        //api logout.doIfSuccess{
        delay(2000) //api logout simulation
        return repo.logout(name)
        //}
    }
}