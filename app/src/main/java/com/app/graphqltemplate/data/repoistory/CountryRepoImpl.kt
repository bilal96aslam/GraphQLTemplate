package com.app.graphqltemplate.data.repoistory

import com.apollographql.apollo.ApolloClient
import com.app.graphqltemplate.domain.repository.CountryRepo
import src.main.qraphql.ContinentFetchingQuery

class CountryRepoImpl(private val apolloClient: ApolloClient) : CountryRepo {
    override suspend fun getContinents(): Result<ContinentFetchingQuery.Data> {
        return try {
            val response = apolloClient.query(ContinentFetchingQuery()).execute()
            response.data?.let {
                Result.success(it)
            } ?: run {
                Result.failure(
                    response.exception ?: Exception("Something went wrong please try again!")
                )
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}