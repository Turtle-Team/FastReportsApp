package com.iubip.fastreportsapp.model

data class Folder(
    val createdTime: String,
    val creatorUserId: String,
    val editedTime: String,
    val editorUserId: String,
    val id: String,
    val name: String,
    val parentId: String,
    val size: Int,
    val status: String,
    val statusReason: String,
    val subscriptionId: String,
    val type: String
)