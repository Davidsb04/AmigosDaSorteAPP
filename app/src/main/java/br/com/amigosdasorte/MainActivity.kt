package br.com.amigosdasorte

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.amigosdasorte.model.SignInModel
import br.com.amigosdasorte.ui.screens.Main
import br.com.amigosdasorte.ui.screens.SignIn
import br.com.amigosdasorte.ui.screens.SignUp
import br.com.amigosdasorte.ui.theme.AmigosDaSorteAPPTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AmigosDaSorteAPPTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "signIn") {
                        composable("signIn") {
                            SignIn(
                                //navController = navController,
                                onSignUpClick = { navController.navigate("signUp") }
                            )
                        }
                        composable("signUp") {
                            SignUp()
                        }
                        composable("main") {
                            Main()
                        }
                    }
                }
            }
        }
    }
}