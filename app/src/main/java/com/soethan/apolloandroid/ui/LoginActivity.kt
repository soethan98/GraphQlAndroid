package com.soethan.apolloandroid.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.soethan.apolloandroid.R
import com.soethan.apolloandroid.utils.InjectorUtils
import com.soethan.apolloandroid.viewmodel.LoginViewModel
import com.soethan.apolloandroid.viewmodelfactory.LoginViewModelFactory
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity

class LoginActivity : AppCompatActivity() {


    private lateinit var loginViewModel: LoginViewModel
    private lateinit var loginViewModelFactory: LoginViewModelFactory


    private lateinit var client: ApolloClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        clickNext.setOnClickListener {
            if (textEditLayout.text!!.isEmpty()) {
                textInputLayout.error = "Please Enter your name"
            } else {
                loginProgress.visibility = View.VISIBLE
                loginUser(textEditLayout.text.toString())

            }
        }


    }


    private fun loginUser(userName: String) {

        loginViewModelFactory = InjectorUtils.provideLoginViewModelFactory(userName)
        loginViewModel =
            ViewModelProviders.of(this, loginViewModelFactory).get(LoginViewModel::class.java)


        loginViewModel.checkUserList().observe(this, Observer { it ->
            //TODO : Really good handling login and create new user
            if (it.isNotEmpty()) {
                startActivity<MainActivity>(
                    "username" to it[0].name(),
                    "id" to it[0].id()
                )
            } else {
                loginViewModel.createNewUser().observe(
                    this, Observer {
                        startActivity<MainActivity>(
                            "username" to it.name(),
                            "id" to it.id()
                        )
                    }
                )
            }







        })
    }




}
