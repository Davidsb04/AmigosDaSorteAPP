package br.com.amigosdasorte.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.amigosdasorte.model.APIResponse
import br.com.amigosdasorte.model.SignUpRequest
import br.com.amigosdasorte.util.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AccountViewModel : ViewModel() {
    private val _signUpSuccess = MutableLiveData<Boolean>()
    val signUpSuccess: LiveData<Boolean> get() = _signUpSuccess

    private val _signUpErrorMessage = MutableLiveData<String?>()
    val signUpErrorMessage: LiveData<String?> get() = _signUpErrorMessage

    fun createUser(email: String, name: String, username: String, password: String){
        val apiService = RetrofitClient.apiService
        val signUpRequest = SignUpRequest(email, name, username, password)

        viewModelScope.launch(Dispatchers.IO) {
            val call = apiService.createUser(signUpRequest)
            call.enqueue(object : Callback<APIResponse> {
                override fun onResponse(
                    call: Call<APIResponse>,
                    response: Response<APIResponse>
                ) {
                    if (response.code() == 400){
                        _signUpSuccess.postValue(false)
                        _signUpErrorMessage.postValue("Todos os campos são obrigatórios.")
                    } else if (response.code() == 409) {
                        _signUpSuccess.postValue(false)
                        _signUpErrorMessage.postValue("E-mail ou nome de usuário já utilizados.")
                    } else{
                        _signUpSuccess.postValue(true)
                        _signUpErrorMessage.postValue(null)
                    }
                }

                override fun onFailure(call: Call<APIResponse>, t: Throwable) {
                    _signUpSuccess.postValue(false)
                    _signUpErrorMessage.postValue("Erro de rede: ${t.message}")
                }
            })
        }
    }

    fun clearCreateUserResult(){
        _signUpErrorMessage.value = null
    }
}