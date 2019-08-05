package com.architecture.stickynotes.data.network.responses

import com.architecture.stickynotes.data.db.entities.Quote

data class QuotesResponse(
    val isSuccessful: Boolean,
    val quotes: List<Quote>
)