package com.iubip.fastreportsapp.repository

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.iubip.fastreportsapp.alerts.ExportDialog
import com.iubip.fastreportsapp.api.ApiService
import com.iubip.fastreportsapp.model.*
import javax.inject.Inject

class FastReportRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getFolder(): Folder = apiService.getFolder()

    suspend fun getContentFolder(): ContentFolder = apiService.getContentFolder()

    suspend fun getContentReports(): ContentReport = apiService.getContentReport()

    suspend fun getContentExports(): ContentExport = apiService.getContentExport()

    suspend fun getApiKeys(): ApiKey = apiService.getApiKey()

    suspend fun getContentGroups(): ContentGroup = apiService.getContentGroups()

    suspend fun getFolderTemplatesByid(id: String): ContentFolder =
        apiService.getFolderTemplatesById(id = id)

    suspend fun getFolderReportsByid(id: String): ContentReport = apiService.getFolderReportsById(id = id)

    suspend fun getFolderExportsByid(id: String): ContentExport =
        apiService.getFolderExportsById(id = id)

    suspend fun downloadTemplateFile(): String = apiService.downloadTemplateFile()

    suspend fun deleteFolderTemplate(id: String) =
        kotlin.runCatching { apiService.deleteFolderTemplate(id = id) }.getOrElse {  }

    suspend fun deleteFileTemplate(id: String) =
        kotlin.runCatching { apiService.deleteFileTemplate(id = id) }

    suspend fun deleteFileExport(id: String) =
        kotlin.runCatching { apiService.deleteFileExport(id = id) }

    suspend fun deleteFolderExport(id: String) =
        kotlin.runCatching { apiService.deleteFolderExport(id = id) }

    suspend fun deleteFolderReport(id: String) =
        kotlin.runCatching { apiService.deleteFolderReport(id = id) }

    suspend fun deleteFileReport(id: String) =
        kotlin.runCatching { apiService.deleteFileReport(id = id) }

    suspend fun renameFileTemplate(id: String, name: String) = kotlin.runCatching { apiService.renameFileTemplate(id = id, name = name) }

    suspend fun renameFileReport(id: String, name: String) = kotlin.runCatching { apiService.renameFileReport(id = id, name = name) }

    suspend fun renameFileExport(id: String, name: String) = kotlin.runCatching { apiService.renameFileExport(id = id, name = name) }

    suspend fun createFolderTemplate(id: String, name: String) =
        kotlin.runCatching { apiService.createFolderTemplate(id = id, name = name) }


    suspend fun copyFileFromTemplate(folderId: String, id: String) =
        kotlin.runCatching { apiService.copyFileFromTemplate(folderId = folderId, id = id) }

    suspend fun copyFileFromReport(folderId: String, id: String) =
        kotlin.runCatching { apiService.copyFileFromReport(folderId = folderId, id = id) }

    suspend fun copyFileFromExport(folderId: String, id: String) =
        kotlin.runCatching { apiService.copyFileFromExport(folderId = folderId, id = id) }

    suspend fun toExportFile(id: String, folder: String)= kotlin.runCatching {apiService.toExportFile(id = id, folder = folder)}

}