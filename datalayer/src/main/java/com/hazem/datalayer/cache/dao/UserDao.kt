package com.hazem.datalayer.cache.dao

import com.hazem.corelayer.model.ResultData
import com.hazem.corelayer.model.User
import com.hazem.datalayer.cache.entity.UserEntity

interface UserDao {
    suspend fun insert(user:User):ResultData<Boolean>
    suspend fun getUser(user:User):ResultData<UserEntity?>
}