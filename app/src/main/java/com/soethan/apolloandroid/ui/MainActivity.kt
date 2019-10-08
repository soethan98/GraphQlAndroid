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
import com.soethan.apolloandroid.fragment.CreateToDo
import com.soethan.apolloandroid.utils.InjectorUtils
import com.soethan.apolloandroid.viewmodel.MainViewModel
import com.soethan.apolloandroid.viewmodelfactory.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), AddCompletion, ToDoClick {


    private lateinit var mainViewModel: MainViewModel
    private lateinit var mainViewModelFactory: MainViewModelFactory


    override fun onDeleteClick(todoId: String) {

        mainViewModel.deleteToDoList(todoId).observe(this, Observer {
            requestToDoList()
        })


    }

    override fun onToDoClick(todoId: String) {

        val ft = supportFragmentManager
        val dialogFragment = CreateToDo.newInstance(userId!!, todoId)
        dialogFragment.show(ft, "dialog")

    }

    override fun onAddComplete(boolean: Boolean) {


        if (boolean) requestToDoList()

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



        mainViewModelFactory = InjectorUtils.provideMainViewModel(userId!!)
        mainViewModel =
            ViewModelProviders.of(this, mainViewModelFactory).get(MainViewModel::class.java)



        requestToDoList()


        createToDo.setOnClickListener {
            val ft = supportFragmentManager
            val dialogFragment = CreateToDo.newInstance(userId!!)
            dialogFragment.show(ft, "dialog")

        }


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


    private fun requestToDoList() {

        mainViewModel.getToDosList().observe(this, Observer {
            mainProgress.visibility = View.GONE
            setUpRecyclerView(it)
        })


    }


}
