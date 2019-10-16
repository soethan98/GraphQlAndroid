package com.soethan.apolloandroid.ui

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.soethan.apolloandroid.R
import com.soethan.apolloandroid.factory.ViewModelFactory
import com.soethan.apolloandroid.repository.FakeRepository
import com.soethan.apolloandroid.utils.InjectorUtils
import com.soethan.apolloandroid.viewmodel.LoginViewModel
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class LoginActivity : DaggerAppCompatActivity() {


    private lateinit var loginViewModel: LoginViewModel

    @Inject
    internal lateinit var loginViewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)







        loginViewModel =
            ViewModelProviders.of(this, loginViewModelFactory).get(LoginViewModel::class.java)



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

//        loginViewModel.checkUserList(userName)

        loginViewModel.checkUserList(userName).observe(this, Observer {
            Log.i("e","${it.size}")
        })



//        loginViewModel.checkUserList(userName).observe(this, Observer { it ->
//
//            if (it.isNotEmpty()) {
//                startActivity<MainActivity>(
//                    "username" to it[0].name(),
//                    "id" to it[0].id()
//                )
//            }
//            } else {
//                loginViewModel.createNewUser(userName).observe(
//                    this, Observer {
//                        startActivity<MainActivity>(
//                            "username" to it.name(),
//                            "id" to it.id()
//                        )
//                    }
//                )
//            }


//
//
//
//
//

    }
}





