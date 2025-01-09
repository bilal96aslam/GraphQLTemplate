package com.app.graphqltemplate.data.repoistory

import com.apollographql.apollo.ApolloClient
import com.app.graphqltemplate.data.repoistory.Constant.ERROR_TEXT
import com.app.graphqltemplate.domain.repository.CountryRepo
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

object Constant {
    const val ERROR_TEXT = "Something went wrong please try again!"
}