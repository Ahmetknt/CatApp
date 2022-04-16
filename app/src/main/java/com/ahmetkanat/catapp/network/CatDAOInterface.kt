package com.ahmetkanat.catapp.network

import android.database.Observable
import com.ahmetkanat.catapp.model.Cat
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET

interface CatDAOInterface {

    @GET("breeds?api_key=a75e92c1-0b57-4127-9e1e-db328dafb529")
    fun getData() : Single<List<Cat>>

}