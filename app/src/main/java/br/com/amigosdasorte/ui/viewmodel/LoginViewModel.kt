package br.com.amigosdasorte.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.amigosdasorte.model.SignInRequest
import br.com.amigosdasorte.model.APIResponse
import br.com.amigosdasorte.util.apiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {

    private val _responseSuccess = MutableLiveData<Boolean>()
    val responseSuccess: LiveData<Boolean> get() = _responseSuccess

    private val _responseErrorMessage = MutableLiveData<String?>()
    val responseErrorMessage: LiveData<String?> get() = _responseErrorMessage

    fun login(email: String, password: String) {
        val signInRequest = SignInRequest(email, password)

        viewModelScope.launch(Dispatchers.IO) {
            val call = apiService.login(signInRequest)
            call.enqueue(object : Callback<APIResponse> {
                override fun onResponse(
                    call: Call<APIResponse>,
                    response: Response<APIResponse>
                ) {
                    if (response.code() == 400) {
                        _responseSuccess.postValue(false)
                        _responseErrorMessage.postValue("Insira todos os campos.")
                    } else if(response.code() == 401 || response.code() == 404) {
                        _responseSuccess.postValue(false)
                        _responseErrorMessage.postValue("E-mail ou senha incorretos.")
                    } else if (response.code() == 500){
                        _responseSuccess.postValue(false)
                        _responseErrorMessage.postValue("Não foi possível efetuar o login.")
                    } else{
                        _responseSuccess.postValue(true)
                        _responseErrorMessage.postValue(null)
                    }
                }

                override fun onFailure(call: Call<APIResponse>, t: Throwable) {
                    _responseSuccess.postValue(false)
                    _responseErrorMessage.postValue("Erro de rede: ${t.message}")
                }
            })
        }
    }

    fun clearLoginResult() {
        _responseErrorMessage.value = null
    }
}
