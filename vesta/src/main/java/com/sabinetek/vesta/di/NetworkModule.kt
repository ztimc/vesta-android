package com.sabinetek.vesta.di

import android.content.Context
import com.sabinetek.vesta.data.api.AuthService
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import java.io.File
import java.util.Date
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun providesAuthService(
        retrofit: Retrofit,
    ): AuthService {
        return retrofit
            .create(AuthService::class.java)
    }

    @Singleton
    @Provides
    fun providesRetrofit(
        converterFactory: MoshiConverterFactory,
        client: OkHttpClient,
        baseUrl: String,
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(converterFactory)
            .client(client)
            .baseUrl(baseUrl)
            .build()
    }

    @Singleton
    @Provides
    fun providesMoshiConverterFactory(moshi: Moshi): MoshiConverterFactory {
        return MoshiConverterFactory.create(moshi)
    }

    @Singleton
    @Provides
    fun providesMoshi(): Moshi {
        return Moshi.Builder()
            .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
            .build()
    }

    @Singleton
    @Provides
    fun providesOkHttpClient(
        interceptor: HttpLoggingInterceptor,
        cache: Cache,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .followRedirects(true)
            .followSslRedirects(true)
            .retryOnConnectionFailure(true)
            .cache(cache)
            .build()
    }

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor { message ->
            Timber.i(message)
        }.apply {
            level = BODY
        }
    }

    @Singleton
    @Provides
    fun providesCache(@ApplicationContext context: Context): Cache {
        return Cache(
            File(context.cacheDir, CLIENT_CACHE_DIRECTORY),
            CLIENT_CACHE_SIZE,
        )
    }

    private companion object {
        private const val CLIENT_CACHE_SIZE = 2 * 10 * 1024 * 1024L // 20 MiB
        private const val CLIENT_CACHE_DIRECTORY = "https-json-cache"
    }
}