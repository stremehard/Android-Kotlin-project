package com.example.viacinema.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.viacinema.usecase.ApiResponse
import com.example.viacinema.usecase.CatFact
import com.example.viacinema.usecase.CatsApi
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListViewModel(private val api: CatsApi): ViewModel() {

    private val _data: MutableLiveData<List<CatFact>> = MutableLiveData()

    val data: LiveData<List<CatFact>> get() = _data

    init {
        api.getCatFacts(100).enqueue(object : Callback<ApiResponse> {
            override fun onFailure(call: Call<ApiResponse>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<ApiResponse>?, response: Response<ApiResponse>?) {
                Log.d("myLogs", "response = ${response?.body()?.data}")
                _data.postValue(response?.body()?.data)
            }
        })
    }
}