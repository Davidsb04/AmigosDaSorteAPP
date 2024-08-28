package br.com.amigosdasorte.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.amigosdasorte.model.APIResponse
import br.com.amigosdasorte.model.SignUpRequest
import br.com.amigosdasorte.model.User
import br.com.amigosdasorte.util.RetrofitClient
import br.com.amigosdasorte.util.apiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AccountViewModel : ViewModel() {
    private val _responseSuccess = MutableLiveData<Boolean>()
    val responseSuccess: LiveData<Boolean> get() = _responseSuccess

    private val _responseErrorMessage = MutableLiveData<String?>()
    val responseErrorMessage: LiveData<String?> get() = _responseErrorMessage

    private val _responseSuccessMessage = MutableLiveData<String?>()
    val responseSuccessMessage: LiveData<String?> get() = _responseSuccessMessage

    private val _userData = MutableLiveData<User?>()
    val userData : LiveData<User?> = _userData

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
                        _responseSuccess.postValue(false)
                        _responseErrorMessage.postValue("Todos os campos são obrigatórios.")
                    } else if (response.code() == 409) {
                        _responseSuccess.postValue(false)
                        _responseErrorMessage.postValue("E-mail ou nome de usuário já utilizados.")
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

    fun deleteUser(){

        viewModelScope.launch(Dispatchers.IO) {
            val call = apiService.deleteUser()
            call.enqueue(object : Callback<APIResponse> {
                override fun onResponse(call: Call<APIResponse>, response: Response<APIResponse>) {
                    if (response.code() == 401){
                        _responseErrorMessage.postValue("Nenhum usuário conectado foi encontrado.")
                    }  else if (response.code() == 404){
                        _responseErrorMessage.postValue("Usuário não encontrado.")
                    } else{
                        _responseErrorMessage.postValue(null)
                    }
                }

                override fun onFailure(call: Call<APIResponse>, t: Throwable) {
                    _responseErrorMessage.postValue("Erro de rede: ${t.message}")
                }
            })
        }
    }

    fun currentUser(){
        viewModelScope.launch(Dispatchers.IO) {
            val call = apiService.currentUser()
            call.enqueue(object : Callback<User>{
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful){
                        _userData.postValue(response.body())
                    } else if (response.code() == 401){
                        _responseErrorMessage.postValue("Nenhum usuário conectado foi encontrado.")
                    } else if (response.code() == 404){
                        _responseErrorMessage.postValue("Usuário não encontrado.")
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    _responseErrorMessage.postValue("Erro de rede: ${t.message}")
                }
            })
        }
    }

    fun updateUser(email: String, name: String, username: String){
        val updateRequest = User(email, name, username)

        viewModelScope.launch(Dispatchers.IO) {
            val call = apiService.updateUser(updateRequest)
            call.enqueue(object : Callback<APIResponse>{
                override fun onResponse(call: Call<APIResponse>, response: Response<APIResponse>) {
                    if (response.isSuccessful){
                        _responseSuccessMessage.postValue("Perfil atualizado com sucesso.")
                        _responseSuccess.postValue(true)
                        _responseErrorMessage.postValue(null)
                    } else if (response.code() == 400){
                        _responseErrorMessage.postValue("Todos os campos são obrigatórios.")
                        _responseSuccess.postValue(false)
                    } else if (response.code() == 401) {
                        _responseErrorMessage.postValue("Nenhum usuário conectado foi encontrado.")
                        _responseSuccess.postValue(false)
                    } else if (response.code() == 404) {
                        _responseErrorMessage.postValue("Usuário não encotrado.")
                        _responseSuccess.postValue(false)
                    } else if (response.code() == 409) {
                        _responseErrorMessage.postValue("E-mail ou nome de usuário já utilizado.")
                        _responseSuccess.postValue(false)
                    }
                }

                override fun onFailure(call: Call<APIResponse>, t: Throwable) {
                    _responseErrorMessage.postValue("Erro de rede: ${t.message}")
                    _responseSuccess.postValue(false)
                }
            })
        }
    }

    fun clearCreateUserResult(){
        _responseErrorMessage.value = null
    }

    fun clearUpdateResult(){
        _responseErrorMessage.value = null
        _responseSuccessMessage.value = null
    }
}