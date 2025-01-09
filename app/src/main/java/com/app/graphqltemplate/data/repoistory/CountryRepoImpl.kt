package com.app.graphqltemplate.data.repoistory

import com.apollographql.apollo.ApolloClient
import com.app.graphqltemplate.domain.repository.CountryRepo
import com.app.graphqltemplate.utils.Constant.ERROR_TEXT
import src.main.qraphql.ContinentFetchingQuery
import src.main.qraphql.FetchDetailsQuery

class CountryRepoImpl(private val apolloClient: ApolloClient) : CountryRepo {
    override suspend fun getContinents(): Result<ContinentFetchingQuery.Data> {
        return try {
            val response = apolloClient.query(ContinentFetchingQuery()).execute()
            response.data?.let {
                Result.success(it)
            } ?: run {
                Result.failure(
                    response.exception ?: Exception(ERROR_TEXT)
                )
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getContinentDetail(code: String): Result<FetchDetailsQuery.Data> {
        return try {
            val response = apolloClient.query(FetchDetailsQuery(code)).execute()
            response.data?.let {
                Result.success(it)
            } ?: run {
                Result.failure(
                    response.exception ?: Exception(ERROR_TEXT)
                )
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
