package com.soethan.apolloandroid.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.soethan.apolloandroid.repository.ToDoRepository

class LoginViewModel(private val toDoRepository: ToDoRepository, var username: String) :
    ViewModel() {

    fun createNewUser() = toDoRepository.createrNewUser(username)
    fun checkUserList() = toDoRepository.authenticateUser(username)




}