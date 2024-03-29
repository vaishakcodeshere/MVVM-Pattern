package com.architecture.stickynotes.data.network

import android.content.Context
import android.net.ConnectivityManager
import com.architecture.stickynotes.util.NoInternetException
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionInterceptor(
    context: Context
) : Interceptor{


    private val applicationContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {

        if (!isInternetAvailable()){

            throw NoInternetException("Make sure you have an active internet connection")

        }

        return chain.proceed(chain.request())
    }


    private fun isInternetAvailable(): Boolean{

        val connectivityManage =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        connectivityManage.activeNetworkInfo.also {
            return it != null && it.isConnected
        }

    }

}