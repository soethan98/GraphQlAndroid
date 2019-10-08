package com.soethan.apolloandroid.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.soethan.apolloandroid.repository.ToDoRepository
import com.soethan.apolloandroid.viewmodel.CreateFragViewModel

class CreateFragViewModelFactory(
    private val toDoRepository: ToDoRepository, private val userId: String

) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return CreateFragViewModel(toDoRepository, userId) as T
    }
}