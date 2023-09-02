package com.hazem.datalayer.cache.dao

import com.hazem.corelayer.model.ErrorEntity
import com.hazem.corelayer.model.ResultData
import com.hazem.corelayer.model.User
import com.hazem.datalayer.cache.entity.UserEntity
import com.hazem.datalayer.mapper.toEntity
import com.hazem.datalayer.mapper.toUser
import io.objectbox.Box
import javax.inject.Inject

class UserDaoImpl @Inject constructor(
    private val userBox: Box<UserEntity>?
):UserDao {
    override suspend fun insert(user: User): ResultData<Boolean> {
        return try {
            userBox?.put(user.toEntity())//mapping should be outside dao
            ResultData.Success(true)

        } catch (e:Exception){
            ResultData.Error(ErrorEntity.InternalError(e.message?:"Error in insertion"))
        }
    }

    override suspend fun deleteUser(): ResultData<Boolean> {
        return try {
            userBox?.removeAll()//mapping should be outside dao
            ResultData.Success(true)
        } catch (e:Exception){
            ResultData.Error(ErrorEntity.InternalError(e.message?:"Error in delete user"))
        }
    }

    override suspend fun getUser(): ResultData<User?> {
        return try {
            ResultData.Success(userBox?.all?.get(0)?.toUser())

        } catch (e:Exception){
            ResultData.Error(ErrorEntity.InternalError(e.message?:"Error in getting user"))
        }
    }
}