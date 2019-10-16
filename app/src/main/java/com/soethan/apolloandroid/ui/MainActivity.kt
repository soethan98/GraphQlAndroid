package com.soethan.apolloandroid.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.soethan.apolloandroid.R
import com.soethan.apolloandroid.ToDoAdapter
import com.soethan.apolloandroid.delegate.AddCompletion
import com.soethan.apolloandroid.delegate.ToDoClick
import com.soethan.apolloandroid.utils.InjectorUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), AddCompletion, ToDoClick {




    override fun onDeleteClick(todoId: String) {



    }

    override fun onToDoClick(todoId: String) {


    }

    override fun onAddComplete(boolean: Boolean) {



    }


    private lateinit var todoAdapter: ToDoAdapter

    private var todos: List<GetToDosByIdQuery.Todo>? = null

    private var name: String? = null
    private var userId: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        name = intent.extras!!.getString("username")
        userId = intent.extras!!.getString("id")

        title = name








    }


    private fun setUpRecyclerView(todoList: List<GetToDosByIdQuery.Todo>) {

        todos = todoList




        mainRecycler.apply {
            setHasFixedSize(true)
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            todoAdapter = ToDoAdapter(todos!!, this@MainActivity)
            todoAdapter.notifyDataSetChanged()

            adapter = todoAdapter

        }

    }




}
