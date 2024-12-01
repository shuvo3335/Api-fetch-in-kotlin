package com.example.kotlinapplication

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiInterface {
    @Headers("x-rapidapi-key: f4424940e4msh723a989a6b0aa9ap10bd01jsn0e61dcd392ad" ,
            "x-rapidapi-host: deezerdevs-deezer.p.rapidapi.com")
    @GET("search")
    fun getData(@Query("q") query: String) : Call<MyData>
}
