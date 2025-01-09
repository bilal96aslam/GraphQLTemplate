package com.app.graphqltemplate.di

import com.app.graphqltemplate.data.repoistory.CountryRepoImpl
import com.app.graphqltemplate.domain.repository.CountryRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ApiModule {
    @Binds
    @Singleton
    abstract fun provideApiHelper(countryRepoImpl: CountryRepoImpl): CountryRepo
}