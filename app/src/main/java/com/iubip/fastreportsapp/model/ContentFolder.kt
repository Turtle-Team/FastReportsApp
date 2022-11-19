package com.iubip.fastreportsapp.model

data class ContentFolder(
    val files: List<File>,
    val skip: Int,
    val count: Int,
    val take: Int
)

data class File (
    val id: String,
    val createdTime: String,
    val editedTime: String,
    val name: String,
    val parentId: String,
    val size: Int,
    val status: String,
    val statusReason: String,
    val subscriptionId: String,
    val type: String
)