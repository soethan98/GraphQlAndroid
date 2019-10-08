package com.soethan.apolloandroid.viewmodel

import androidx.lifecycle.ViewModel
import com.soethan.apolloandroid.repository.ToDoRepository

class CreateFragViewModel(
    private val toDoRepository: ToDoRepository,
    var userId: String

) : ViewModel() {

    fun getToDoById(toDoId: String) = toDoRepository.getToDoById(toDoId)
    fun updateToDo(toDoId: String, title: String, description: String) =
        toDoRepository.updateToDo(toDoId, title, description)

    fun createToDo(title: String, description: String) =
        toDoRepository.createToDo(userId, title, description)
}