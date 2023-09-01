package com.hazem.datalayer.cache.dao

import com.hazem.corelayer.model.ErrorEntity
import com.hazem.corelayer.model.ResultData
import com.hazem.corelayer.model.User
import com.hazem.datalayer.cache.entity.UserEntity
import com.hazem.datalayer.mapper.toEntity
import io.objectbox.Box
import javax.inject.Inject

class UserDaoImpl @Inject constructor(
    private val userBox: Box<UserEntity>?
):UserDao {
    override suspend fun insert(user: User): ResultData<Boolean> {
        return try {
            userBox?.put(user.toEntity())
            ResultData.Success(true)

        } catch (e:Exception){
            ResultData.Error(ErrorEntity.InternalError(e.message?:"Error in insertion"))
        }
    }

    override suspend fun getUser(user: User): ResultData<UserEntity?> {
        return try {
            ResultData.Success(userBox?.all?.get(0))

        } catch (e:Exception){
            ResultData.Error(ErrorEntity.InternalError(e.message?:"Error in insertion"))
        }
    }
}