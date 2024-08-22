package br.com.amigosdasorte.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.amigosdasorte.model.SignInRequest
import br.com.amigosdasorte.model.APIResponse
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
        val signInRequest = SignInRequest(email, password)

        viewModelScope.launch(Dispatchers.IO) {
            val call = apiService.login(signInRequest)
            call.enqueue(object : Callback<APIResponse> {
                override fun onResponse(
                    call: Call<APIResponse>,
                    response: Response<APIResponse>
                ) {
                    if (response.code() == 400) {
                        _loginSuccess.postValue(false)
                        _loginErrorMessage.postValue("Insira todos os campos.")
                    } else if(response.code() == 401 || response.code() == 404) {
                        _loginSuccess.postValue(false)
                        _loginErrorMessage.postValue("E-mail ou senha incorretos.")
                    } else if (response.code() == 500){
                        _loginSuccess.postValue(false)
                        _loginErrorMessage.postValue("Não foi possível efetuar o login.")
                    } else{
                        _loginSuccess.postValue(true)
                        _loginErrorMessage.postValue(null)
                    }
                }

                override fun onFailure(call: Call<APIResponse>, t: Throwable) {
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
