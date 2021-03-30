package com.moanes.nytimes.data.network

import com.moanes.nytimes.data.models.BaseResponse
import retrofit2.http.GET

interface Remote {
    @GET("mostpopular/v2/viewed/7.json")
    suspend fun getMostPopularArticles(): BaseResponse
}