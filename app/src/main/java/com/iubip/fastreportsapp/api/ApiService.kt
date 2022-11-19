package com.iubip.fastreportsapp.api

import com.iubip.fastreportsapp.model.*
import com.iubip.fastreportsapp.utils.Constants
import retrofit2.http.*

interface ApiService {

    // Получить все содержимое каталога
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

    @GET("rp/v1/Templates/Folder/637799b25f620ebfce9a08ec")
    suspend fun getFolder(
        @Header("Authorization") authorization: String = Constants.BASIC_AUTH,
    ): Folder

    @GET("manage/v1/Subscriptions/6377865f5f620ebfce9a07ce/groups")
    suspend fun getContentGroups(
        @Header("Authorization") authorization: String = Constants.BASIC_AUTH,
    ): ContentGroup

    @GET("manage/v1/UserSettings")
    suspend fun getContentUsers(
        @Header("Authorization") authorization: String = Constants.BASIC_AUTH,
    ): ContentUser


    @GET("manage/v1/ApiKeys")
    suspend fun getApiKey(
        @Header("Authorization") authorization: String = Constants.BASIC_AUTH,
    ): ApiKey



    // Получить содержимое каталога по ID
    @GET("rp/v1/Templates/Folder/{id}/ListFolderAndFiles?skip=0&take=24&orderBy=None&desc=false&searchPattern=")
    suspend fun getFolderTemplatesById(
        @Header("Authorization") authorization: String = Constants.BASIC_AUTH,
        @Path("id") id: String
    ): ContentFolder

    @GET("rp/v1/Reports/Folder/{id}/ListFolderAndFiles?skip=0&take=24&orderBy=None&desc=false&searchPattern=")
    suspend fun getFolderReportsById(
        @Header("Authorization") authorization: String = Constants.BASIC_AUTH,
        @Path("id") id: String
    ): ContentReport

    @GET("rp/v1/Exports/Folder/{id}/ListFolderAndFiles?skip=0&take=24&orderBy=None&desc=false&searchPattern=")
    suspend fun getFolderExportsById(
        @Header("Authorization") authorization: String = Constants.BASIC_AUTH,
        @Path("id") id: String
    ): ContentFolder




    // Загрузка файлов
    @Headers("Content-Type: text/xml")
    @GET("https://fastreport.cloud/download/t/6378b5485f620ebfce9a2116")
    suspend fun downloadTemplateFile(
        @Header("Authorization") authorization: String = Constants.BASIC_AUTH,
//        @Path("id") id: String
    ): String



    // Удалить каталог
    @DELETE("rp/v1/Templates/Folder/{id}")
    suspend fun deleteFolderTemplate(
        @Header("Authorization") authorization: String = Constants.BASIC_AUTH,
        @Path("id") id: String
    )

    @DELETE("rp/v1/Exports/Folder/{id}")
    suspend fun deleteFolderExport(
        @Header("Authorization") authorization: String = Constants.BASIC_AUTH,
        @Path("id") id: String
    )

    @DELETE("rp/v1/Reports/Folder/{id}")
    suspend fun deleteFolderReport(
        @Header("Authorization") authorization: String = Constants.BASIC_AUTH,
        @Path("id") id: String
    )



    // Удалить файл
    @DELETE("rp/v1/Templates/File/{id}")
    suspend fun deleteFileTemplate(
        @Header("Authorization") authorization: String = Constants.BASIC_AUTH,
        @Path("id") id: String
    )

    @DELETE("rp/v1/Reports/File/{id}")
    suspend fun deleteFileReport(
        @Header("Authorization") authorization: String = Constants.BASIC_AUTH,
        @Path("id") id: String
    )

    @DELETE("rp/v1/Exports/File/{id}")
    suspend fun deleteFileExport(
        @Header("Authorization") authorization: String = Constants.BASIC_AUTH,
        @Path("id") id: String
    )



    // Переименовать файл
    @PUT("rp/v1/Templates/File/{id}/Rename")
    suspend fun renameFileTemplate(
        @Header("Host") host: String = "fastreport.cloud",
        @Header("Authorization") authorization: String = Constants.BASIC_AUTH,
        @Header("Content-Type") type: String = "application/json",
        @Path("id") id: String,
        @Body name: String
    )

    @POST("rp/v1/Exports/File/{id}/Rename")
    suspend fun renameFileExport(
        @Header("Authorization") authorization: String = Constants.BASIC_AUTH,
        @Path("id") id: String
    )

    @POST("rp/v1/Reports/Folder/{id}/Rename")
    suspend fun renameFileReport(
        @Header("Authorization") authorization: String = Constants.BASIC_AUTH,
        @Path("id") id: String
    )




    // Создание каталогов
    @POST("rp/v1/Templates/Folder/6377865f5f620ebfce9a07cb/Folder")
    suspend fun createFolderTemplate(
        @Header("Authorization") authorization: String = Constants.BASIC_AUTH,
        @Path("name") name: String
    )

    @POST("rp/v1/Reports/Folder/6377865f5f620ebfce9a07cb/Folder")
    suspend fun createFolderReport(
        @Header("Authorization") authorization: String = Constants.BASIC_AUTH,
        @Path("name") name: String
    )

    @POST("rp/v1/Exports/Folder/{id}/Folder")
    suspend fun createFolderExport(
        @Header("Authorization") authorization: String = Constants.BASIC_AUTH,
        @Path("name") name: String
    )



    // Скопировать файл в выбранный каталог
    @POST("rp/v1/Exports/File/{id}/Copy/{folderId}")
    suspend fun copyFileFromExport(
        @Header("Authorization") authorization: String = Constants.BASIC_AUTH,
        @Path("folderId") folderId: String,
        @Path("id") id: String
    )

    @POST("rp/v1/Reports/File/{id}/Copy/{folderId}")
    suspend fun copyFileFromReport(
        @Header("Authorization") authorization: String = Constants.BASIC_AUTH,
        @Path("folderId") folderId: String,
        @Path("id") id: String
    )

    @POST("rp/v1/Templates/File/{id}/Copy/{folderId}")
    suspend fun copyFileFromTemplate(
        @Header("Authorization") authorization: String = Constants.BASIC_AUTH,
        @Path("folderId") folderId: String,
        @Path("id") id: String
    )
}