package br.com.amigosdasorte.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.amigosdasorte.ui.theme.AmigosDaSorteAPPTheme
import br.com.amigosdasorte.ui.theme.DefaultTextFieldLabelStyle
import br.com.amigosdasorte.ui.theme.DefaultTextFieldTextStyle
import br.com.amigosdasorte.ui.theme.defaultTextFieldColors

@Composable
fun HomeScreen(){

    Column(
        Modifier
            .background(color = Color(0xFFFFFFFF))
            .fillMaxSize(),
    ) {
        var text by remember { mutableStateOf("") }

        TextField(
            value = text,
            onValueChange = {
                text = it
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
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    AmigosDaSorteAPPTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            HomeScreen()
        }
    }
}