package com.example.retrofitsignup.Api

import com.example.retrofitsignup.models.DefaultResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Api {
    
    @FormUrlEncoded
    @POST("login")
    fun createUser(
        @Field("email") email:String,
        @Field("password") password:String

    ): Call<DefaultResponse>
}