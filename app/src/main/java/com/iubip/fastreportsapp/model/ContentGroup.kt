package com.iubip.fastreportsapp.model

data class ContentGroup(
    val count: Int,
    val groups: List<Group>,
    val skip: Int,
    val take: Int
)

data class Group(
    val id: String,
    val name: String,
    val subscriptionId: String
)