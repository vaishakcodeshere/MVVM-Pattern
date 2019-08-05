package com.architecture.stickynotes.ui.home.quotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.architecture.stickynotes.data.repository.QuotesRepository
import com.architecture.stickynotes.data.repository.UserRepository

@Suppress("UNCHECKED_CAST")
class QuotesViewModelFactory (

    private val repository: QuotesRepository

) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return QuotesViewModel(repository) as T
    }

}