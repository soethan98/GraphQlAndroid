package com.soethan.apolloandroid.utils

import android.content.Context
import com.soethan.apolloandroid.repository.ToDoRepository
import com.soethan.apolloandroid.viewmodelfactory.CreateFragViewModelFactory
import com.soethan.apolloandroid.viewmodelfactory.LoginViewModelFactory
import com.soethan.apolloandroid.viewmodelfactory.MainViewModelFactory

object InjectorUtils {

    private fun provideToDoRepository(): ToDoRepository? {
        return ToDoRepository.getInstance()
    }


     fun provideLoginViewModelFactory(userName: String): LoginViewModelFactory {
        val repository = provideToDoRepository()
        return LoginViewModelFactory(repository!!, userName)
    }

     fun provideCreateFragmentFactory(
        userId: String
    ): CreateFragViewModelFactory {
        val repository = provideToDoRepository()
        return CreateFragViewModelFactory(repository!!, userId)
    }

     fun provideMainViewModel(userId: String): MainViewModelFactory {
        val repository = provideToDoRepository()
        return MainViewModelFactory(repository!!, userId)
    }

}