package com.iubip.fastreportsapp.api

import com.iubip.fastreportsapp.model.ContentFolder
import com.iubip.fastreportsapp.model.Folder
import com.iubip.fastreportsapp.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {

    @GET("rp/v1/Templates/Folder/637799b25f620ebfce9a08ec")
    suspend fun getFolder(
        @Header("Authorization") authorization: String = Constants.BASIC_AUTH,
    ): Folder

    @GET("rp/v1/Templates/Folder/6377865f5f620ebfce9a07cb/ListFolderAndFiles?skip=0&take=24&orderBy=None&desc=false&searchPattern=")
    suspend fun getContentFolder(
        @Header("Authorization") authorization: String = Constants.BASIC_AUTH,
    ): ContentFolder
}