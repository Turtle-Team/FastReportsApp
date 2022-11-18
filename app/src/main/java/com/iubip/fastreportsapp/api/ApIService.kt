package com.iubip.fastreportsapp.api

import com.iubip.fastreportsapp.model.Folder
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface ApIService {

    @GET("rp/v1/Templates/Folder/637799b25f620ebfce9a08ec")
    suspend fun getpppp (@Header("Authorization") authorization: String) :Folder

}