package com.soethan.apolloandroid.utils

import android.content.Context

object InjectorUtils {}

//    private fun provideToDoRepository(): ToDoRepository? {
//        return ToDoRepository.getInstance()
//    }
//
//
//     fun provideLoginViewModelFactory(userName: String): LoginViewModelFactory {
//        val repository = provideToDoRepository()
//        return LoginViewModelFactory(repository!!, userName)
//    }
//
//     fun provideCreateFragmentFactory(
//        userId: String
//    ): CreateFragViewModelFactory {
//        val repository = provideToDoRepository()
//        return CreateFragViewModelFactory(repository!!, userId)
//    }
//
//     fun provideMainViewModel(userId: String): MainViewModelFactory {
//        val repository = provideToDoRepository()
//        return MainViewModelFactory(repository!!, userId)
//    }
//
//}