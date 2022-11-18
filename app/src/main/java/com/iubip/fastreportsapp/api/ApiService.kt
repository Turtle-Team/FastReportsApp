package com.iubip.fastreportsapp.api

import com.iubip.fastreportsapp.model.*
import com.iubip.fastreportsapp.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ApiService {

    @GET("rp/v1/Templates/Folder/637799b25f620ebfce9a08ec")
    suspend fun getFolder(
        @Header("Authorization") authorization: String = Constants.BASIC_AUTH,
    ): Folder

    @GET("rp/v1/Templates/Folder/6377865f5f620ebfce9a07cb/ListFolderAndFiles?skip=0&take=24&orderBy=None&desc=false&searchPattern=")
    suspend fun getContentFolder(
        @Header("Authorization") authorization: String = Constants.BASIC_AUTH,
    ): ContentFolder

    @GET("rp/v1/Reports/Folder/6377865f5f620ebfce9a07cc/ListFolderAndFiles?skip=0&take=24&orderBy=None&desc=false&searchPattern=")
    suspend fun getContentReport(
        @Header("Authorization") authorization: String = Constants.BASIC_AUTH,
    ): ContentReport

    @GET("rp/v1/Exports/Folder/6377865f5f620ebfce9a07cd/ListFolderAndFiles?skip=0&take=24&orderBy=None&desc=false&searchPattern=")
    suspend fun getContentExport(
        @Header("Authorization") authorization: String = Constants.BASIC_AUTH,
    ): ContentExport

    @GET("manage/v1/ApiKeys")
    suspend fun getApiKey(
        @Header("Authorization") authorization: String = Constants.BASIC_AUTH,
    ): ApiKeys

    @GET("manage/v1/Subscriptions/6377865f5f620ebfce9a07ce/groups")
    suspend fun getContentGroups(
        @Header("Authorization") authorization: String = Constants.BASIC_AUTH,
    ): ContentGroup

    @GET("manage/v1/UserSettings")
    suspend fun getContentUsers(
        @Header("Authorization") authorization: String = Constants.BASIC_AUTH,
    ): ContentUser

    @GET("rp/v1/Templates/Folder/{id}/ListFolderAndFiles?skip=0&take=24&orderBy=None&desc=false&searchPattern=")
    suspend fun getFolderTemplatesById(
        @Header("Authorization") authorization: String = Constants.BASIC_AUTH,
        @Path("id") id: String
    ): ContentFolder

    @GET("rp/v1/Reports/Folder/{id}/ListFolderAndFiles?skip=0&take=24&orderBy=None&desc=false&searchPattern=")
    suspend fun getFolderReportsById(
        @Header("Authorization") authorization: String = Constants.BASIC_AUTH,
        @Path("id") id: String
    ): ContentFolder

    @GET("https://fastreport.cloud/api/rp/v1/Exports/Folder/{id}/ListFolderAndFiles?skip=0&take=24&orderBy=None&desc=false&searchPattern=")
    suspend fun getFolderExportsById(
        @Header("Authorization") authorization: String = Constants.BASIC_AUTH,
        @Path("id") id: String
    ): ContentFolder
}