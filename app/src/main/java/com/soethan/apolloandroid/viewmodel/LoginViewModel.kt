package com.soethan.apolloandroid.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.soethan.apolloandroid.repository.FakeRepository
import com.soethan.apolloandroid.repository.ToDoRepository
import javax.inject.Inject

class LoginViewModel @Inject constructor(var toDoRepository: ToDoRepository) :
    ViewModel() {

//
//
//    var todoUser: MutableLiveData<CreateUserMutation.CreateUser> = MutableLiveData()
//    var todoUsers: MutableLiveData<List<CheckUserQuery.User>> = MutableLiveData()
//
//
//    fun createNewUser(username: String):LiveData<CreateUserMutation.CreateUser>{
//        graphqlClient.mutate(
//            CreateUserMutation
//                .builder().username(
//                    username
//                ).build()
//        ).enqueue(object : ApolloCall.Callback<CreateUserMutation.Data>() {
//            override fun onResponse(response: Response<CreateUserMutation.Data>) {
//                if (response.data()?.createUser() != null) {
//
//                    todoUser.postValue(response.data()?.createUser())
//
//                }
//            }
//
//
//            override fun onFailure(e: ApolloException) {
//                Log.i("HiLogin", e.message)
//            }
//
//        })
//
//        return todoUser
//    }
//
//
//    fun checkUserList(username: String):LiveData<List<CheckUserQuery.User>>{
//        graphqlClient.query(
//            CheckUserQuery
//                .builder().name(
//                    username
//                ).build()
//        ).enqueue(object : ApolloCall.Callback<CheckUserQuery.Data>() {
//            override fun onResponse(response: Response<CheckUserQuery.Data>) {
//                val todoUsers = MediatorLiveData<List<CheckUserQuery.User>>()
//                todoUsers.postValue(response.data()?.users())
//
//
//            }
//
//
//            override fun onFailure(e: ApolloException) {
//                Log.i("HiLogin", e.message)
//            }
//
//        })
//
//        return todoUsers
//    }
//

//    fun createNewUser(username: String) = toDoRepository.createrNewUser(username)
    fun checkUserList(username: String) : LiveData<List<CheckUserQuery.User>> {

        Log.i("viewmodel",username)


        return toDoRepository.authenticateUser(username)

    }


}