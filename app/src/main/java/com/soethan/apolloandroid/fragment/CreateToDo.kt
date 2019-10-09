package com.soethan.apolloandroid.fragment


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException

import com.soethan.apolloandroid.R
import com.soethan.apolloandroid.api.GraphqlClient
import kotlinx.android.synthetic.main.fragment_create_to_do.*
import kotlinx.android.synthetic.main.fragment_create_to_do.view.*
import com.soethan.apolloandroid.delegate.AddCompletion
import com.soethan.apolloandroid.utils.InjectorUtils
import com.soethan.apolloandroid.viewmodel.CreateFragViewModel
import com.soethan.apolloandroid.viewmodelfactory.CreateFragViewModelFactory


class CreateToDo : DialogFragment() {


    private var id: String? = null
    private var toDoId: String? = null
    private lateinit var addCompletion: AddCompletion
    private lateinit var viewModel: CreateFragViewModel
    private lateinit var viewModelFactory: CreateFragViewModelFactory

    companion object {
        fun newInstance(id: String, todoId: String = ""): CreateToDo {
            val frag = CreateToDo()
            val args = Bundle()
            args.putString("id", id)
            args.putString("todoId", todoId)
            frag.arguments = args
            return frag


        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        id = arguments?.getString("id")
        toDoId = arguments?.getString("todoId")




        addCompletion = activity as AddCompletion

        // TODO : "id!!" is dangerous.
        // TODO : Avoid "!!" whenever possible
        viewModelFactory = InjectorUtils.provideCreateFragmentFactory(id!!)
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(CreateFragViewModel::class.java)


        if (toDoId != null && toDoId!!.isNotEmpty()) {
            getToDoFromGraphQl()
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_create_to_do, container, false)





        view.clickAdd.setOnClickListener {

            if (toDoId != null && titleEditLayout.text!!.isNotEmpty()) {
                updateToDo()
            }

            if (toDoId!!.isEmpty() && titleEditLayout.text!!.isNotEmpty()){
                addToGraphQl(titleEditLayout.text.toString(), descEditLayout.text.toString())
            }else{
                titleInputLayout.error = "Please Fill some title"

            }

        }

        return view
    }

    private fun addToGraphQl(title: String, desc: String) {


        viewModel.createToDo(title, desc).observe(this@CreateToDo, Observer {
            addCompletion.onAddComplete(true)
            dismiss()
        })




    }


    private fun getToDoFromGraphQl() {


        viewModel.getToDoById(toDoId!!).observe(this@CreateToDo, Observer {
            titleEditLayout.setText(it.title())
            descEditLayout.setText(it.description())
        })



    }


    private fun updateToDo() {

        viewModel.updateToDo(
            toDoId!!,
            titleEditLayout.text.toString(),
            descEditLayout.text.toString()
        ).observe(this@CreateToDo,
            Observer {
                addCompletion.onAddComplete(true)
                dismiss()
            })

    }


}
