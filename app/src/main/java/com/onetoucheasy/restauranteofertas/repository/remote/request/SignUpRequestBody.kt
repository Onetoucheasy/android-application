package com.onetoucheasy.restauranteofertas.repository.remote.request

data class SignUpRequestBody(
    val name: String,
    val email: String,
    val password: String,
    val type: String
)
