package br.com.amigosdasorte.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.amigosdasorte.ui.theme.AmigosDaSorteAPPTheme

@Composable
fun ProfileScreen(){
    Text(text = "Just a test.")
}

@Preview
@Composable
fun ProfileScreenPreview() {
    AmigosDaSorteAPPTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            ProfileScreen()
        }
    }
}