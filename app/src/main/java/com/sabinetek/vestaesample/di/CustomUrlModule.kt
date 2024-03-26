package com.sabinetek.vestaesample.di

import com.sabinetek.vesta.di.UrlProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class CustomUrlModule {

    @Provides
    fun provideUrlProvider(): UrlProvider {
        return object: UrlProvider {
            override fun getBaseUrl() = "http://192.168.5.88:8888"
        }
    }
}