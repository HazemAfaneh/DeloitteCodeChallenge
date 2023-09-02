package com.hazem.datalayer.mapper

import com.hazem.corelayer.model.User
import com.hazem.datalayer.cache.entity.UserEntity

fun User.toEntity(): UserEntity {
    return UserEntity(
        fullName = fullName,
        email = email,
        nationalId = nationalId,
        phoneNumber = phoneNumber,
        dateOfBirth = dateOfBirth,
        password = password,
    )
}
fun UserEntity.toUser(): User {
    return User(
        fullName = fullName,
        email = email,
        nationalId = nationalId,
        phoneNumber = phoneNumber,
        dateOfBirth = dateOfBirth,
        password = password,
    )
}