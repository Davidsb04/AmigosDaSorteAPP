package br.com.amigosdasorte.model


import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("login/login")
    fun login(@Body signInRequest: SignInRequest): Call<APIResponse>

    @POST("account/create_user")
    fun createUser(@Body signUpRequest: SignUpRequest): Call<APIResponse>
}