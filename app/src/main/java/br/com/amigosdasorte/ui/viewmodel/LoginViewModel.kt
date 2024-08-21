package br.com.amigosdasorte.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.amigosdasorte.model.ApiService
import br.com.amigosdasorte.model.LoginRequest
import br.com.amigosdasorte.model.LoginResponse
import br.com.amigosdasorte.util.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {

    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean> get() = _loginSuccess

    private val _loginErrorMessage = MutableLiveData<String?>()
    val loginErrorMessage: LiveData<String?> get() = _loginErrorMessage

    fun login(email: String, password: String) {
        val apiService = RetrofitClient.apiService
        val loginRequest = LoginRequest(email, password)

        viewModelScope.launch(Dispatchers.IO) {
            val call = apiService.login(loginRequest)
            call.enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    if (response.isSuccessful) {
                        _loginSuccess.postValue(true)
                        _loginErrorMessage.postValue(null)
                    } else {
                        _loginSuccess.postValue(false)
                        _loginErrorMessage.postValue("E-mail ou senha incorretos.")
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    _loginSuccess.postValue(false)
                    _loginErrorMessage.postValue("Erro de rede: ${t.message}")
                }
            })
        }
    }

    fun clearLoginResult() {
        _loginErrorMessage.value = null
    }
}
