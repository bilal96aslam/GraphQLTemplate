package com.app.graphqltemplate.domain.repository

import src.main.qraphql.ContinentFetchingQuery

interface CountryRepo {
    suspend fun getContinents(): Result<ContinentFetchingQuery.Data>
}