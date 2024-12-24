package com.mschroeder.fetchapp.data

import com.mschroeder.fetchapp.data.models.FetchResponseModel
import retrofit2.http.GET

interface FetchService {
    @GET("/hiring.json")
    suspend fun getList(): List<FetchResponseModel>
}