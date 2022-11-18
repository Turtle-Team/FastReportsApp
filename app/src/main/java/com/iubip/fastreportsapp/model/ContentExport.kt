package com.iubip.fastreportsapp.model

data class ContentExport(
    val count: Int,
    val files: List<File>,
    val skip: Int,
    val take: Int
)

data class ExportFile(
    val createdTime: String,
    val editedTime: String,
    val id: String,
    val name: String,
    val size: Int,
    val status: String,
    val statusReason: String,
    val type: String
)