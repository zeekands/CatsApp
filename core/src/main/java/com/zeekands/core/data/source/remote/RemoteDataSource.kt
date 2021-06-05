package com.zeekands.core.data.source.remote

import com.zeekands.core.data.source.remote.network.ApiResponse
import com.zeekands.core.data.source.remote.network.ApiService
import com.zeekands.core.data.source.remote.response.ListCatsResponse
import com.zeekands.core.utils.Constant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: ApiService
) {
    private val apiKey = Constant.API_KEY

    suspend fun getGames(): Flow<ApiResponse<ListCatsResponse>> {
        return flow {
            try {
                val response = apiService.getCats(apiKey)
                if (response.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}