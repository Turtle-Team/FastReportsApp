package com.iubip.fastreportsapp.repository

import com.iubip.fastreportsapp.api.ApIService
import com.iubip.fastreportsapp.model.Folder
import javax.inject.Inject

class FastReportRepository @Inject constructor(private val apIService: ApIService) {
    suspend fun getwtf(): Folder = apIService.getpppp("f73otbo11nghupahfi53fwk11dbu1ak6w8saxjrbgm5pw8jynsao")
}