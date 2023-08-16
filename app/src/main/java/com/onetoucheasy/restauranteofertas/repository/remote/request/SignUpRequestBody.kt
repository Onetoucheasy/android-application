package com.onetoucheasy.restauranteofertas.repository.remote.request

data class SignUpRequestBody(
    val name: String,
    val password: String,
    val email: String,
    val isCompany: Boolean
)
