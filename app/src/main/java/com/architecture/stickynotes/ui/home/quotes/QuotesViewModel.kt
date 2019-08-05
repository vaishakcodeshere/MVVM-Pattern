package com.architecture.stickynotes.ui.home.quotes

import androidx.lifecycle.ViewModel;
import com.architecture.stickynotes.data.repository.QuotesRepository
import com.architecture.stickynotes.util.lazyDeferred

class QuotesViewModel(
    repository: QuotesRepository
) : ViewModel() {

    val quotes by lazyDeferred {
        repository.getQuotes()
    }
}
