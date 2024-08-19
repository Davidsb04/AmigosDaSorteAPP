package br.com.amigosdasorte.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.amigosdasorte.model.SignUpModel
import br.com.amigosdasorte.ui.theme.AmigosDaSorteAPPTheme
import br.com.amigosdasorte.ui.theme.DefaultButtonTextStyle
import br.com.amigosdasorte.ui.theme.DefaultTextFieldLabelStyle
import br.com.amigosdasorte.ui.theme.DefaultTextFieldTextStyle
import br.com.amigosdasorte.ui.theme.defaultButtonColors
import br.com.amigosdasorte.ui.theme.defaultTextFieldColors

@Composable
fun SignUp(/*onSignUpClick: (SignUpModel) -> Unit*/) {
    Column(
        Modifier
            .background(color = Color(0xFFFFFFFF))
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var email by remember {
            mutableStateOf("")
        }

        var name by remember {
            mutableStateOf("")
        }

        var username by remember {
            mutableStateOf("")
        }

        var password by remember {
            mutableStateOf("")
        }


        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Faça seu Cadastro",
                style = TextStyle(
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            )

            TextField(
                value = email,
                onValueChange = {
                    email = it
                },
                Modifier
                    .padding(8.dp, 0.dp, 8.dp, 0.dp)
                    .fillMaxWidth(),
                colors = defaultTextFieldColors(),
                textStyle = DefaultTextFieldTextStyle,
                label = {
                    Text(
                        text = "E-mail",
                        style = DefaultTextFieldLabelStyle
                    )
                }
            )

            TextField(
                value = name,
                onValueChange = {
                    name = it
                },
                Modifier
                    .padding(8.dp, 0.dp, 8.dp, 0.dp)
                    .fillMaxWidth(),
                colors = defaultTextFieldColors(),
                textStyle = DefaultTextFieldTextStyle,
                label = {
                    Text(
                        text = "Nome Completo",
                        style = DefaultTextFieldLabelStyle
                    )
                }
            )

            TextField(
                value = username,
                onValueChange = {
                    username = it
                },
                Modifier
                    .padding(8.dp, 0.dp, 8.dp, 0.dp)
                    .fillMaxWidth(),
                colors = defaultTextFieldColors(),
                textStyle = DefaultTextFieldTextStyle,
                label = {
                    Text(
                        text = "Nome de Usuário",
                        style = DefaultTextFieldLabelStyle
                    )
                }
            )

            TextField(
                value = password,
                onValueChange = {
                    password = it
                },
                Modifier
                    .padding(8.dp, 0.dp, 8.dp, 0.dp)
                    .fillMaxWidth(),
                colors = defaultTextFieldColors(),
                textStyle = DefaultTextFieldTextStyle,
                visualTransformation = PasswordVisualTransformation(),
                label = {
                    Text(
                        text = "Senha",
                        style = DefaultTextFieldLabelStyle
                    )
                }
            )

            Button(
                onClick = {
                    /*onSignUpClick(
                        SignUpModel(
                            email,
                            name,
                            username,
                            password
                        )
                    )*/
                },
                Modifier
                    .padding(8.dp, 0.dp, 8.dp, 0.dp)
                    .fillMaxWidth(),
                colors = defaultButtonColors(),

                ) {
                Text(
                    text = "Cadastrar",
                    style = DefaultButtonTextStyle
                )
            }
        }
    }
}

@Preview
@Composable
fun SignUpPreview() {
    AmigosDaSorteAPPTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            SignUp(/*onSignUpClick = {}*/)
        }
    }
}