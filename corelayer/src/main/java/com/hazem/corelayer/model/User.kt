package com.hazem.corelayer.model

data class User(
    val fullName: String? = null,
    val email: String,
    val nationalId: Long? = null,
    val phoneNumber: Long? = null,
    val dateOfBirth: Long? = null,
    val password: String
)
