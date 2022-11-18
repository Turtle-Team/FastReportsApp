package com.iubip.fastreportsapp.model

data class ContentReport(
    val count: Int,
    val files: List<ContentFile>,
    val skip: Int,
    val take: Int
)

data class ContentFile(
    val createdTime: String,
    val editedTime: String,
    val id: String,
    val name: String,
    val size: Int,
    val status: String,
    val statusReason: String,
    val type: String
)