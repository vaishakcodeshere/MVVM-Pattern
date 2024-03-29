package com.architecture.stickynotes

import android.app.Application
import com.architecture.stickynotes.data.db.AppDatabase
import com.architecture.stickynotes.data.network.MyApi
import com.architecture.stickynotes.data.network.NetworkConnectionInterceptor
import com.architecture.stickynotes.data.preferences.PreferenceProvider
import com.architecture.stickynotes.data.repository.QuotesRepository
import com.architecture.stickynotes.data.repository.UserRepository
import com.architecture.stickynotes.ui.auth.AuthViewModelFactory
import com.architecture.stickynotes.ui.home.profile.ProfileViewModelFactory
import com.architecture.stickynotes.ui.home.quotes.QuotesViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MVVMApplication : Application(), KodeinAware {


    override val kodein = Kodein.lazy {

        import(androidXModule(this@MVVMApplication))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { MyApi(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { PreferenceProvider(instance()) }
        bind() from singleton { UserRepository(instance(), instance()) }
        bind() from provider { AuthViewModelFactory(instance()) }
        bind() from provider { ProfileViewModelFactory(instance()) }
        bind() from singleton { QuotesRepository(instance(), instance(), instance()) }
        bind() from singleton { QuotesViewModelFactory(instance()) }

    }


}