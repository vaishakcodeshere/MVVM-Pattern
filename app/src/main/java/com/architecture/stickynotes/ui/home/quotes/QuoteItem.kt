package com.architecture.stickynotes.ui.home.quotes

import com.architecture.stickynotes.R
import com.architecture.stickynotes.data.db.entities.Quote
import com.architecture.stickynotes.databinding.ItemQuoteBinding
import com.xwray.groupie.databinding.BindableItem

class QuoteItem(
    private val quote: Quote
) : BindableItem<ItemQuoteBinding>(){

    override fun getLayout() = R.layout.item_quote

    override fun bind(viewBinding: ItemQuoteBinding, position: Int) {
        viewBinding.setQuote(quote)
    }
}