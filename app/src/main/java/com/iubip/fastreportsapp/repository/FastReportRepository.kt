package com.iubip.fastreportsapp.repository

import com.iubip.fastreportsapp.api.ApiService
import com.iubip.fastreportsapp.model.*
import javax.inject.Inject

class FastReportRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getFolder(): Folder = apiService.getFolder()

    suspend fun getContentFolder(): ContentFolder = apiService.getContentFolder()

    suspend fun getContentReports(): ContentReport = apiService.getContentReport()

    suspend fun getContentExports(): ContentExport = apiService.getContentExport()

    suspend fun getApiKeys(): ApiKeys = apiService.getApiKey()

    suspend fun getContentGroups(): ContentGroup = apiService.getContentGroups()

    suspend fun getFolderTemplatesByid(id: String): ContentFolder = apiService.getFolderTemplatesById(id = id)

    suspend fun getFolderReportsByid(id: String): ContentFolder = apiService.getFolderReportsById(id = id)

    suspend fun getFolderExportsByid(id: String): ContentFolder = apiService.getFolderExportsById(id = id)

    suspend fun downloadTemplateFile(): String = apiService.downloadTemplateFile()
}