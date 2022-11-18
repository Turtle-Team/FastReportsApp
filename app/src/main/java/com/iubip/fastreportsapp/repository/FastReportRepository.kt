package com.iubip.fastreportsapp.repository

import com.iubip.fastreportsapp.api.ApiService
import com.iubip.fastreportsapp.model.ContentFolder
import com.iubip.fastreportsapp.model.Folder
import javax.inject.Inject

class FastReportRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getFolder(): Folder = apiService.getFolder()

    suspend fun getContentFolder(): ContentFolder = apiService.getContentFolder()
}