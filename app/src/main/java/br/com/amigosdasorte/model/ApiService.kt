package br.com.amigosdasorte.model


import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface ApiService {
    @POST("login/login")
    fun login(@Body signInRequest: SignInRequest): Call<APIResponse>

    @POST("account/create_user")
    fun createUser(@Body signUpRequest: SignUpRequest): Call<APIResponse>

    @DELETE("account/delete_user")
    fun deleteUser(): Call<APIResponse>

    @GET("login/current_user")
    fun currentUser(): Call<User>

    @PUT("account/update_user")
    fun updateUser(@Body updateRequest: User): Call<APIResponse>
}