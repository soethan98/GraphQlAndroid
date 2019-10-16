package com.soethan.apolloandroid.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloCallback
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.ApolloQueryCall
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.soethan.apolloandroid.api.GraphqlClient
import com.soethan.apolloandroid.utils.NetworkBoundResource
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ToDoRepository @Inject constructor(private val apolloClient: ApolloClient){




//    var apolloClient = GraphqlClient.setupApollo()




//   var newtodo: MutableLiveData<UploadToDoMutation.UpdateUser> = MutableLiveData()
//    var updatetodo: MutableLiveData<UpdateToDoMutation.UpdateTodo> = MutableLiveData()
//    var deletetodo: MutableLiveData<DeleteToDoMutation.DeleteTodo> = MutableLiveData()


//    companion object {
//        var sInstance: ToDoRepository? = null
//        val LOCK = Object()
//
//        fun getInstance(): ToDoRepository? {
//            Log.d("ToDoRepository", "Getting the repository")
//            if (sInstance == null) {
//                synchronized(LOCK) {
//
//                    sInstance = ToDoRepository()
//                    Log.d("ToDoRepository", "Made new repository")
//
//                }
//            }
//            return sInstance
//        }
//
//
//    }


//    fun getToDosByUserId(userID: String): LiveData<List<GetToDosByIdQuery.Todo>> {
//        apolloClient.query(
//            GetToDosByIdQuery
//                .builder().id(userID).build()
//        ).enqueue(object : ApolloCall.Callback<GetToDosByIdQuery.Data>() {
//            override fun onResponse(response: Response<GetToDosByIdQuery.Data>) {
//
//                todoList.postValue(response.data()?.user()?.todos())
//            }
//
//            override fun onFailure(e: ApolloException) {
//
//
//            }
//        })
//
//        return todoList
//
//
//    }

//    fun getToDoById(todoId: String):LiveData<GetToDoByIdQuery.Todo> {
//        apolloClient.query(
//            GetToDoByIdQuery
//                .builder().id(todoId).build()
//        ).enqueue(object : ApolloCall.Callback<GetToDoByIdQuery.Data>() {
//            override fun onResponse(response: Response<GetToDoByIdQuery.Data>) {
//
//
//                todoById.postValue(response.data()?.todo())
//            }
//
//            override fun onFailure(e: ApolloException) {
//
//                Log.i("Hello", "${e.message}")
//
//            }
//        })
//
//        return todoById
//
//    }

    fun createrNewUser(userName: String) {
        apolloClient.mutate(
            CreateUserMutation
                .builder().username(
                    userName
                ).build()
        ).enqueue(object : ApolloCall.Callback<CreateUserMutation.Data>() {
            override fun onResponse(response: Response<CreateUserMutation.Data>) {
                if (response.data()?.createUser() != null) {

//                    todoUser.postValue(response.data()?.createUser())

                }
            }


            override fun onFailure(e: ApolloException) {
                Log.i("HiLogin", e.message)
            }

        })

    }


//    fun updateToDo(
//        toDoId: String,
//        title: String,
//        description: String
//    ): LiveData<UpdateToDoMutation.UpdateTodo> {
//        apolloClient.mutate(
//            UpdateToDoMutation.builder().id(
//                toDoId
//            ).title(title).description(description).build()
//        )?.enqueue(object : ApolloCall.Callback<UpdateToDoMutation.Data>() {
//            override fun onResponse(response: Response<UpdateToDoMutation.Data>) {
//
//                updatetodo.postValue(response.data()?.updateTodo())
//
//            }
//
//            override fun onFailure(e: ApolloException) {
//                Log.d("HiCreate", e.message)
//            }
//        })
//
//        return updatetodo
//
//    }

//    fun createToDo(userID: String, title: String, description: String): LiveData<UploadToDoMutation.UpdateUser> {
//
//        apolloClient.mutate(
//            UploadToDoMutation.builder().id(
//                userID
//            ).title(title).description(description).build()
//        )?.enqueue(object : ApolloCall.Callback<UploadToDoMutation.Data>() {
//            override fun onResponse(response: Response<UploadToDoMutation.Data>) {
//                Log.d("HiCreateAdd", "${response.data()?.updateUser()?.id()}")
//
//                newtodo.postValue(response.data()?.updateUser())
//
//
//            }
//
//            override fun onFailure(e: ApolloException) {
//                Log.d("HiCreate", e.message)
//            }
//        })
//
//        return newtodo
//    }





    fun authenticateUser(username: String): LiveData<List<CheckUserQuery.User>> {


       val todoUsers = MutableLiveData<List<CheckUserQuery.User>>()


        apolloClient.query(
            CheckUserQuery
                .builder().name(
                    username
                ).build()
        ).enqueue(object : ApolloCall.Callback<CheckUserQuery.Data>() {
            override fun onResponse(response: Response<CheckUserQuery.Data>) {


//               todoUsers.value =  response.data()?.users()

                Log.i("Login",response.data()!!.users()[0].name())

                todoUsers.postValue(response.data()?.users())




            }


            override fun onFailure(e: ApolloException) {
                Log.i("HiLogin", e.message)
            }

        })

        return todoUsers


    }


//    fun deleteToDo(todoId: String): LiveData<DeleteToDoMutation.DeleteTodo> {
//
//        apolloClient.mutate(
//            DeleteToDoMutation
//                .builder().id(todoId).build()
//
//        ).enqueue(
//            object : ApolloCall.Callback<DeleteToDoMutation.Data>() {
//                override fun onResponse(response: Response<DeleteToDoMutation.Data>) {
//                    deletetodo.postValue(response.data()?.deleteTodo())
//                }
//
//
//                override fun onFailure(e: ApolloException) {
//
//
//                }
//            }
//        )
//
//        return deletetodo
//    }


}