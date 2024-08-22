package br.com.amigosdasorte.model

data class SignUpRequest(
    val email : String,
    val name: String,
    val username: String,
    val password : String
)