package com.soethan.apolloandroid.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.soethan.apolloandroid.repository.ToDoRepository
import com.soethan.apolloandroid.viewmodel.LoginViewModel
import com.soethan.apolloandroid.viewmodel.MainViewModel

class MainViewModelFactory(
    private val repository: ToDoRepository,
    private  val userId:String
) : ViewModelProvider.NewInstanceFactory(){


    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return MainViewModel(repository, userId) as T
    }
}