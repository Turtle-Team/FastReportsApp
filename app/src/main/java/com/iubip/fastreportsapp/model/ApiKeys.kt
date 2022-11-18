package com.iubip.fastreportsapp.model

data class ApiKeys(
    val apiKeys: List<ApiKey>,
    val count: Int
)

data class ApiKey(
    val description: String,
    val expired: String,
    val value: String
)