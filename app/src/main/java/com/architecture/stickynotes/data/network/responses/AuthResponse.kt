package com.architecture.stickynotes.data.network.responses

import com.architecture.stickynotes.data.db.entities.User

data class AuthResponse(

    val isSuccessful: Boolean?,
    val message: String?,
    val user: User?

)