package com.picpay.desafio.android.data.service

import com.picpay.desafio.android.model.User
import retrofit2.http.GET


interface PicPayService {

    @GET("users")
    suspend fun getUsers(): List<User>
}