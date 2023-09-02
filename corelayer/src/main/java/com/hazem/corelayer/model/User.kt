package com.hazem.corelayer.model

data class User(
    var fullName: String? = null,
    var email: String,
    var nationalId: Long? = null,
    var phoneNumber: Long? = null,
    var dateOfBirth: Long? = null,
    var password: String
)
