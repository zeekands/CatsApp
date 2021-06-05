package com.zeekands.core.data.source.remote.network

import com.zeekands.core.data.source.remote.response.ListCatsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("breeds")
    suspend fun getCats(
        @Query("api_key") key: String
    ): ListCatsResponse
}