package com.architecture.stickynotes.ui.auth

import androidx.lifecycle.LiveData
import com.architecture.stickynotes.data.db.entities.User

interface AuthListener {

    fun onStarted()

    fun onSuccess(user: User)

    fun onFailure(message: String)

}