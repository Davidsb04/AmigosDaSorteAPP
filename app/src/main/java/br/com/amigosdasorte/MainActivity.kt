package br.com.amigosdasorte

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.amigosdasorte.ui.screens.MainScreen
import br.com.amigosdasorte.ui.screens.SignIn
import br.com.amigosdasorte.ui.screens.SignUp
import br.com.amigosdasorte.ui.theme.AmigosDaSorteAPPTheme
import br.com.amigosdasorte.ui.viewmodel.AccountViewModel
import br.com.amigosdasorte.ui.viewmodel.LoginViewModel

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
                    val loginViewModel: LoginViewModel = viewModel()
                    val accountViewModel: AccountViewModel = viewModel()


                    NavHost(navController = navController, startDestination = "signIn") {
                        composable("signIn") {
                            SignIn(
                                navController = navController,
                                loginViewModel = loginViewModel,
                            )
                        }
                        composable("signUp") {
                            SignUp(
                                navController = navController,
                                accountViewModel = accountViewModel,
                            )
                        }
                        composable("main") {
                            MainScreen()
                        }
                    }
                }
            }
        }
    }
}