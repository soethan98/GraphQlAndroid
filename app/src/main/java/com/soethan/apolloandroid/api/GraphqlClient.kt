package com.soethan.apolloandroid.api

import com.apollographql.apollo.ApolloClient
import okhttp3.OkHttpClient


const val BASE_URL = "https://us1.prisma.sh/lin-min-phyo-b9618e/sample/dev"

 object GraphqlClient{

     fun setupApollo(): ApolloClient {
        val okHttp = OkHttpClient
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
        return ApolloClient.builder()
            .serverUrl(BASE_URL)
            .okHttpClient(okHttp)
            .build()
    }

}