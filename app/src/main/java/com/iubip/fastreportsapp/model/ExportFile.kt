package com.iubip.fastreportsapp.model

data class FileToExport(
    val fileName: String,
    val folderId: String,
    val format: String,
    val locale: String
)