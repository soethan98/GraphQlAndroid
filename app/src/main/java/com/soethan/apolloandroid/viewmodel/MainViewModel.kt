package com.soethan.apolloandroid.viewmodel

import androidx.lifecycle.ViewModel
import com.soethan.apolloandroid.repository.ToDoRepository

class MainViewModel(
    private val toDoRepository: ToDoRepository,
    var userId: String
) : ViewModel() {

    fun getToDosList() = toDoRepository.getToDosByUserId(userId)

    fun deleteToDoList(todoId:String) = toDoRepository.deleteToDo(todoId)

}