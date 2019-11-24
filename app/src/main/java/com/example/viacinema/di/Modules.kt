package com.example.viacinema.di

import android.content.Context
import androidx.room.Room
import com.example.viacinema.usecase.UserDatabase
import com.example.viacinema.usecase.CatsApi
import com.example.viacinema.viewmodels.ListViewModel
import com.example.viacinema.viewmodels.LoginViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private val view_models = module {
    viewModel { ListViewModel(get()) }
    viewModel { LoginViewModel(get()) }
}

private val my_modules = module {
    single { createDatabase(get()) }
}


private fun createDatabase(context: Context): UserDatabase =
    Room.databaseBuilder(context, UserDatabase::class.java, "user_database")
        .allowMainThreadQueries()
        .build()


val myModules get() = my_modules + remoteDatasourceModule + view_models


val remoteDatasourceModule = module {
    // provided web components
    single { createOkHttpClient() }
    // Fill property
    single { createWebService<CatsApi>(get(), "http://catfact.ninja/") }
}


object DatasourceProperties {
    const val SERVER_URL = "SERVER_URL"
}

fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
    return OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor).build()
}

inline fun <reified T> createWebService(okHttpClient: OkHttpClient, url: String): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    return retrofit.create(T::class.java)
}