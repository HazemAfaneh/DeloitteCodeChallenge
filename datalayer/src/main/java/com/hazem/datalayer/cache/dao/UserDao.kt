package com.hazem.datalayer.cache.dao

import com.hazem.corelayer.model.ResultData
import com.hazem.corelayer.model.User

interface UserDao {
    suspend fun insert(user:User):ResultData<Boolean>
    suspend fun deleteUser():ResultData<Boolean>
    suspend fun getUser():ResultData<User?>
}