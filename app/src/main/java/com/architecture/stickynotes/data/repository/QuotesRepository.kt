package com.architecture.stickynotes.data.repository

import android.annotation.TargetApi
import android.os.Build
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.architecture.stickynotes.data.db.AppDatabase
import com.architecture.stickynotes.data.db.entities.Quote
import com.architecture.stickynotes.data.network.MyApi
import com.architecture.stickynotes.data.network.SafeApiRequest
import com.architecture.stickynotes.data.preferences.PreferenceProvider
import com.architecture.stickynotes.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit


private val MINIMUM_INTERVAL = 6;

class QuotesRepository(
    private val api: MyApi,
    private val db: AppDatabase,
    private val pref: PreferenceProvider
) : SafeApiRequest() {

    private val quotes = MutableLiveData<List<Quote>>()

    init {

        quotes.observeForever {
            saveQuotes(it)
        }
    }

    private suspend fun fetchQuotes() {

        val lastSavedAt = pref.getLastSavedAt()
        if (lastSavedAt == null || isFetchNeeded(LocalDateTime.parse(lastSavedAt))) {
            val response = apiRequest { api.getQuotes() }
            quotes.postValue(response.quotes)
        }

    }

    @TargetApi(Build.VERSION_CODES.O)
    private fun isFetchNeeded(savedAt: LocalDateTime): Boolean {
        return ChronoUnit.HOURS.between(savedAt, LocalDateTime.now()) > MINIMUM_INTERVAL
    }

    @TargetApi(Build.VERSION_CODES.O)
    private fun saveQuotes(it: List<Quote>) {

        Coroutines.io {
            pref.saveLastSavedAt(LocalDateTime.now().toString())
            db.getQuoteDao().saveAllQuotes(it)
        }

    }

    suspend fun getQuotes(): LiveData<List<Quote>> {
        return withContext(Dispatchers.IO) {
            fetchQuotes()
            db.getQuoteDao().getQuotes()
        }
    }
}