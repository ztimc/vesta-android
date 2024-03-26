package com.sabinetek.vesta.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/** Injections for the API. */
@Module
@InstallIn(SingletonComponent::class)
class ApiConfigModule {
  @Provides
  fun provideBaseUrl(urlProvider: UrlProvider) = urlProvider.getBaseUrl()

}
