package com.architecture.stickynotes.data.repository

import com.architecture.stickynotes.data.db.AppDatabase
import com.architecture.stickynotes.data.db.entities.User
import com.architecture.stickynotes.data.network.MyApi
import com.architecture.stickynotes.data.network.SafeApiRequest
import com.architecture.stickynotes.data.network.responses.AuthResponse

class UserRepository(
    private val api: MyApi,
    private val db: AppDatabase
) : SafeApiRequest() {

    suspend fun userLogin(email: String, password: String): AuthResponse {
        return apiRequest { api.userLogin(email, password) }
    }

    suspend fun userSignup(name: String, email: String, password: String): AuthResponse {
        return apiRequest { api.userSignup(name, email, password) }
    }

    suspend fun saveUser(user: User) = db.getUserDao().upsert(user)


    fun getUser() = db.getUserDao().getUser()
}