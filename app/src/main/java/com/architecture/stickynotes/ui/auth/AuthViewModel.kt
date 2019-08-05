package com.architecture.stickynotes.ui.auth

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.architecture.stickynotes.data.repository.UserRepository
import com.architecture.stickynotes.util.ApiException
import com.architecture.stickynotes.util.Coroutines
import com.architecture.stickynotes.util.NoInternetException

class AuthViewModel(
    private val repository: UserRepository
) : ViewModel() {

    var email: String? = null

    var name: String? = null

    var password: String? = null

    var confirmpassword: String? = null

    var authListener: AuthListener? = null

    fun getLoggedInUser() = repository.getUser()

    fun onLoginButtonClick(view: View) {

        authListener?.onStarted()

        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {

            authListener?.onFailure("Invalid Email / Password")

            return
        }

        Coroutines.main {

            try {

                val authResponse = repository.userLogin(email!!, password!!)

                authResponse.user?.let {

                    authListener?.onSuccess(it)
                    repository.saveUser(it)
                    return@main

                }
                authListener?.onFailure(authResponse.message!!)

            } catch (e: ApiException) {
                authListener?.onFailure(e.message!!)
            } catch (e: NoInternetException) {
                authListener?.onFailure(e.message!!)
            }
        }

    }

    fun onSignup(view: View){
        Intent(view.context, SignupActivity::class.java).also {
            view.context.startActivity(it)
        }
    }


    fun onLogin(view: View){
        Intent(view.context, LoginActivity::class.java).also {
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            view.context.startActivity(it)
        }
    }


    fun onSignupButtonClick(view: View) {

        authListener?.onStarted()


        if (name.isNullOrEmpty()) {

            authListener?.onFailure("Name is required")

            return
        }


        if (email.isNullOrEmpty()) {

            authListener?.onFailure("Name is required")

            return
        }


        if (password.isNullOrEmpty()) {

            authListener?.onFailure("Password is required")

            return
        }


        if (password != confirmpassword) {

            authListener?.onFailure("Password did not match")

            return
        }


        Coroutines.main {

            try {

                val authResponse = repository.userSignup(name!!, email!!, password!!)

                authResponse.user?.let {

                    authListener?.onSuccess(it)
                    repository.saveUser(it)
                    return@main
                }
                authListener?.onFailure(authResponse.message!!)

            } catch (e: ApiException) {
                authListener?.onFailure(e.message!!)
            } catch (e: NoInternetException) {
                authListener?.onFailure(e.message!!)
            }

        }

    }

}