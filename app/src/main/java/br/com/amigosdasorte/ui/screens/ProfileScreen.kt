package br.com.amigosdasorte.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.amigosdasorte.ui.theme.AmigosDaSorteAPPTheme
import br.com.amigosdasorte.ui.theme.DefaultButtonTextStyle
import br.com.amigosdasorte.ui.theme.DefaultTextFieldLabelStyle
import br.com.amigosdasorte.ui.theme.DefaultTextFieldTextStyle
import br.com.amigosdasorte.ui.theme.defaultButtonColors
import br.com.amigosdasorte.ui.theme.defaultTextFieldColors
import br.com.amigosdasorte.ui.viewmodel.AccountViewModel
import kotlinx.coroutines.delay


@Composable
fun ProfileScreen(
    accountViewModel: AccountViewModel
){
    val responseSuccess by accountViewModel.responseSuccess.observeAsState()
    val responseErrorMessage by accountViewModel.responseErrorMessage.observeAsState()
    val successMessage by accountViewModel.responseSuccessMessage.observeAsState()
    val user by accountViewModel.userData.observeAsState()

    val showDialog = remember { mutableStateOf(false) }
    var showError by remember { mutableStateOf(false) }
    var showSuccess by remember { mutableStateOf(false) }
    var name by remember { mutableStateOf(user?.name ?: "") }
    var email by remember { mutableStateOf(user?.email ?: "") }
    var username by remember { mutableStateOf(user?.username ?: "") }

    Column (
        Modifier
            .background(color = Color(0xFFFFFFFF))
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column (
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Eai, ${username}!",
                style = TextStyle(
                    fontSize = 38.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
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
                        text = "Nome",
                        style = DefaultTextFieldLabelStyle
                    )
                }
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
                        text = "Nome do Usuário",
                        style = DefaultTextFieldLabelStyle
                    )
                }
            )

            Button(
                onClick = {
                    accountViewModel.updateUser(email, name, username)
                },
                Modifier
                    .padding(8.dp, 0.dp, 8.dp, 0.dp)
                    .fillMaxWidth(),
                colors = defaultButtonColors(),

                ) {
                Text(
                    text = "Atualizar",
                    style = DefaultButtonTextStyle
                )
            }

            TextButton(onClick = {
                showDialog.value = true
            },
                Modifier
                    .padding(4.dp, 4.dp, 4.dp, 0.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Excluir Conta",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Red
                )
            }

            when {
                responseSuccess == true -> {
                    showSuccess = true
                    LaunchedEffect(Unit) {
                        delay(2000)
                        showSuccess = false
                        accountViewModel.clearUpdateResult()
                    }
                }
                responseSuccess != null -> {
                    showError = true
                    LaunchedEffect(Unit) {
                        delay(2000)
                        showError = false
                        accountViewModel.clearUpdateResult()
                    }
                }
            }

            if (showSuccess) {
                successMessage?.let { message ->
                    Text(
                        text = message,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1cc54b),
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }

            if (showError) {
                responseErrorMessage?.let { errorMessage ->
                    Text(
                        text = errorMessage,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Red,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }


            if(showDialog.value){
                AlertDialog(
                    onDismissRequest = { showDialog.value = false },
                    title = { Text(text = "Confirmação de Exclusão")},
                    text = { Text(text = "Você tem certeza que deseja excluir sua conta? Esta ação é irreversível.")},
                    confirmButton = {
                        TextButton(
                            onClick = {
                                accountViewModel.deleteUser()
                                showDialog.value = false
                            }
                        ) {
                            Text(text = "Confirmar")
                        }
                    },
                    dismissButton = {
                        TextButton(
                            onClick = { showDialog.value = false }
                        ) {
                            Text(text = "Cancelar")
                        }
                    }
                )
            }

            LaunchedEffect(Unit) {
                accountViewModel.currentUser()
            }

            LaunchedEffect(user) {
                user?.let {
                    name = it.name
                    email = it.email
                    username = it.username
                }
            }
        }
    }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    AmigosDaSorteAPPTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            ProfileScreen(
                accountViewModel = viewModel()
            )
        }
    }
}