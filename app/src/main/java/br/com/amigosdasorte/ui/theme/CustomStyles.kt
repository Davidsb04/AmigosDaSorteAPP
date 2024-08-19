package br.com.amigosdasorte.ui.theme

import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun defaultTextFieldColors() = TextFieldDefaults.colors(
    unfocusedContainerColor = Color.White,
    focusedContainerColor = Color.White,
    focusedTextColor = Color.Black,
    unfocusedTextColor = Color.Black,
    focusedIndicatorColor = Color.Black
)

val DefaultTextFieldTextStyle = TextStyle(
    fontSize = 16.sp,
    fontWeight = FontWeight.Bold
)

val DefaultTextFieldLabelStyle = TextStyle(
    fontSize = 18.sp,
    fontWeight = FontWeight.Bold,
    color = Color.Black
)

@Composable
fun defaultButtonColors(): ButtonColors = ButtonDefaults.buttonColors(
    containerColor = Color(0xFF1cc54b),
    contentColor = Color.White
)

val DefaultButtonTextStyle = TextStyle(
    fontSize = 18.sp,
    fontWeight = FontWeight.Bold
)
