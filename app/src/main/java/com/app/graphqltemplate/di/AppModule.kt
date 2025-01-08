package com.app.graphqltemplate.di

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.network.okHttpClient
import com.app.graphqltemplate.data.repoistory.CountryRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideApolloClient() = ApolloClient.Builder()
        .serverUrl("https://countries.trevorblades.com/")
        .okHttpClient(OkHttpClient.Builder().build())
        .build()

    @Provides
    fun provideApolloClient(apolloClient: ApolloClient) =
        CountryRepoImpl(apolloClient)
}