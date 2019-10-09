package com.soethan.apolloandroid.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.soethan.apolloandroid.api.GraphqlClient

class ToDoRepository {


    private var graphqlClient: ApolloClient = GraphqlClient.setupApollo()
    var todoList: MutableLiveData<List<GetToDosByIdQuery.Todo>> = MutableLiveData()
    var todoById: MutableLiveData<GetToDoByIdQuery.Todo> = MutableLiveData()
    var todoUser: MutableLiveData<CreateUserMutation.CreateUser> = MutableLiveData()
    var todoUsers: MutableLiveData<List<CheckUserQuery.User>> = MutableLiveData()


    var newtodo: MutableLiveData<UploadToDoMutation.UpdateUser> = MutableLiveData()
    var updatetodo: MutableLiveData<UpdateToDoMutation.UpdateTodo> = MutableLiveData()
    var deletetodo: MutableLiveData<DeleteToDoMutation.DeleteTodo> = MutableLiveData()


    companion object {
        var sInstance: ToDoRepository? = null
        val LOCK = Object()

        fun getInstance(): ToDoRepository? {
            Log.d("ToDoRepository", "Getting the repository")
            if (sInstance == null) {
                synchronized(LOCK) {

                    sInstance = ToDoRepository()
                    Log.d("ToDoRepository", "Made new repository")

                }
            }
            return sInstance
        }


    }

    // TODO: Live data subscription and data fetching should be different method, or the full power of Observer pattern will not be using
    // TODO : 1. Observe a live data from a method
    // TODO : 2. Fetch data from somewhere else, may be just after observing, or after refresh is clicked, or onResume activity
    // TODO : cont'd : We can just create one live data and emit to the same live data whenever data is changed
    fun getToDosByUserId(userID: String): LiveData<List<GetToDosByIdQuery.Todo>> {
        graphqlClient.query(
            GetToDosByIdQuery
                .builder().id(userID).build()
        ).enqueue(object : ApolloCall.Callback<GetToDosByIdQuery.Data>() {
            override fun onResponse(response: Response<GetToDosByIdQuery.Data>) {

                todoList.postValue(response.data()?.user()?.todos())
            }

            override fun onFailure(e: ApolloException) {


            }
        })

        return todoList


    }

    fun getToDoById(todoId: String):LiveData<GetToDoByIdQuery.Todo> {
        graphqlClient.query(
            GetToDoByIdQuery
                .builder().id(todoId).build()
        ).enqueue(object : ApolloCall.Callback<GetToDoByIdQuery.Data>() {
            override fun onResponse(response: Response<GetToDoByIdQuery.Data>) {


                todoById.postValue(response.data()?.todo())
            }

            override fun onFailure(e: ApolloException) {

                Log.i("Hello", "${e.message}")

            }
        })

        return todoById

    }

    fun createrNewUser(userName: String): LiveData<CreateUserMutation.CreateUser> {
        graphqlClient.mutate(
            CreateUserMutation
                .builder().username(
                    userName
                ).build()
        ).enqueue(object : ApolloCall.Callback<CreateUserMutation.Data>() {
            override fun onResponse(response: Response<CreateUserMutation.Data>) {
                if (response.data()?.createUser() != null) {

                    todoUser.postValue(response.data()?.createUser())

                }
            }


            override fun onFailure(e: ApolloException) {
                Log.i("HiLogin", e.message)
            }

        })

        return todoUser
    }


    fun updateToDo(
        toDoId: String,
        title: String,
        description: String
    ): LiveData<UpdateToDoMutation.UpdateTodo> {
        graphqlClient.mutate(
            UpdateToDoMutation.builder().id(
                toDoId
            ).title(title).description(description).build()
        )?.enqueue(object : ApolloCall.Callback<UpdateToDoMutation.Data>() {
            override fun onResponse(response: Response<UpdateToDoMutation.Data>) {

                updatetodo.postValue(response.data()?.updateTodo())

            }

            override fun onFailure(e: ApolloException) {
                Log.d("HiCreate", e.message)
            }
        })

        return updatetodo

    }

    fun createToDo(userID: String, title: String, description: String): LiveData<UploadToDoMutation.UpdateUser> {

        graphqlClient.mutate(
            UploadToDoMutation.builder().id(
                userID
            ).title(title).description(description).build()
        )?.enqueue(object : ApolloCall.Callback<UploadToDoMutation.Data>() {
            override fun onResponse(response: Response<UploadToDoMutation.Data>) {
                Log.d("HiCreateAdd", "${response.data()?.updateUser()?.id()}")

                newtodo.postValue(response.data()?.updateUser())


            }

            override fun onFailure(e: ApolloException) {
                Log.d("HiCreate", e.message)
            }
        })

        return newtodo
    }


    fun authenticateUser(username: String): LiveData<List<CheckUserQuery.User>> {

        Log.i("Repository", username)
        graphqlClient.query(
            CheckUserQuery
                .builder().name(
                    username
                ).build()
        ).enqueue(object : ApolloCall.Callback<CheckUserQuery.Data>() {
            override fun onResponse(response: Response<CheckUserQuery.Data>) {
                todoUsers.postValue(response.data()?.users())


            }


            override fun onFailure(e: ApolloException) {
                Log.i("HiLogin", e.message)
            }

        })

        return todoUsers


    }


    fun deleteToDo(todoId: String): LiveData<DeleteToDoMutation.DeleteTodo> {

        graphqlClient.mutate(
            DeleteToDoMutation
                .builder().id(todoId).build()

        ).enqueue(
            object : ApolloCall.Callback<DeleteToDoMutation.Data>() {
                override fun onResponse(response: Response<DeleteToDoMutation.Data>) {
                    deletetodo.postValue(response.data()?.deleteTodo())
                }


                override fun onFailure(e: ApolloException) {


                }
            }
        )

        return deletetodo
    }


}