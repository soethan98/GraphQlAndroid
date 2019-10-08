package com.soethan.apolloandroid.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.soethan.apolloandroid.repository.ToDoRepository
import com.soethan.apolloandroid.viewmodel.CreateFragViewModel
import com.soethan.apolloandroid.viewmodel.LoginViewModel

class LoginViewModelFactory(
    private val repository: ToDoRepository,
    private val username: String
) : ViewModelProvider.NewInstanceFactory() {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return LoginViewModel(repository, username) as T
    }

}