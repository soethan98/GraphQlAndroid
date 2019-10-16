package com.soethan.apolloandroid.di.module

import com.apollographql.apollo.ApolloClient
import com.soethan.apolloandroid.api.BASE_URL
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
class AppModule{


    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val builder = original.newBuilder().method(
                    original.method(),
                    original.body()
                )

                chain.proceed(builder.build())
            }
            .build()
    }

    @Provides
    @Singleton
    internal fun provideApolloClient(okHttpClient: OkHttpClient): ApolloClient {
        return ApolloClient.builder()
            .serverUrl(BASE_URL)
            .okHttpClient(okHttpClient)
            .build()
    }
}