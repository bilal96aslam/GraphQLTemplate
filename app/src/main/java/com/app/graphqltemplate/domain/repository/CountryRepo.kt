package com.app.graphqltemplate.domain.repository

import src.main.qraphql.ContinentFetchingQuery
import src.main.qraphql.FetchDetailsQuery

interface CountryRepo {
    suspend fun getContinents(): Result<ContinentFetchingQuery.Data>
    suspend fun getContinentDetail(code: String ): Result<FetchDetailsQuery.Data>
}