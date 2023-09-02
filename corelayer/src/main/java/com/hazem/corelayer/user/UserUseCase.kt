package com.hazem.corelayer.user

import com.hazem.corelayer.model.ResultData
import com.hazem.corelayer.model.User

interface UserUseCase {
suspend operator fun invoke(): ResultData<User?>
}