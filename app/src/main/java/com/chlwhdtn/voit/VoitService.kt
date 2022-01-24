package com.chlwhdtn.voit

import retrofit2.Call
import retrofit2.http.*

interface VoitService {

    @GET("/debate/get")
    fun getDebate(
        @Query("id") id: String
    ): Call<VoitResponse>;

    @GET("/debate/gets")
    fun getDebates(
        @Query("page") page: Int
    ): Call<VoitResponse>;

}