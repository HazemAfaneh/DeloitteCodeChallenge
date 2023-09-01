package com.hazem.datalayer.cache.entity

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
@Entity
data class UserEntity (
    @Id
    var id: Long = 0,
    val fullName: String? = null,
    val email: String,
    val nationalId: Long? = null,
    val phoneNumber: Long? = null,
    val dateOfBirth: Long? = null,
    val password: String
)