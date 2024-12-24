package com.mschroeder.fetchapp.data

import com.mschroeder.fetchapp.data.models.FetchResponseModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FetchRepository @Inject constructor(
    private val fetchService: FetchService
) {
    suspend fun getList(): List<FetchResponseModel> {
        return fetchService.getList()
    }
}