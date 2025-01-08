package com.app.graphqltemplate.domain.usecase

import com.app.graphqltemplate.domain.repository.CountryRepo
import com.app.graphqltemplate.utils.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import src.main.qraphql.ContinentFetchingQuery
import javax.inject.Inject

class FetchContinentUseCase @Inject constructor(
    private val countryRepo: CountryRepo
) {
    operator fun invoke(): Flow<ApiResponse<ContinentFetchingQuery.Data>> = flow {
        emit(ApiResponse.Loading())
        val response = countryRepo.getContinents()
        if (response.isSuccess) {
            emit(ApiResponse.Success(data = response.getOrThrow()))
        } else {
            emit(ApiResponse.Error(response.exceptionOrNull()?.message.toString()))
        }
    }.catch {
        emit(ApiResponse.Error(message = it.message.toString()))
    }.flowOn(Dispatchers.IO)

}