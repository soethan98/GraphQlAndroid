package com.soethan.apolloandroid.repository

import android.util.Log
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class FakeRepository @Inject constructor(var apolloClient: ApolloClient) {

    fun provideFakeString():String{
        return "Please Dagger"
    }

    fun provideFakeNumber():Int{
        return 2
    }

    fun getApolloClient(){

        apolloClient.query(
            CheckUserQuery
                .builder().name(
                    "Soe Than"
                ).build()
        ).enqueue(object : ApolloCall.Callback<CheckUserQuery.Data>() {
            override fun onResponse(response: Response<CheckUserQuery.Data>) {

                Log.i("Login11",response.data()!!.users()[0].name())

//                todoUsers.postValue(response.data()?.users())




            }


            override fun onFailure(e: ApolloException) {
                Log.i("HiLogin", e.message)
            }

        })


    }
}