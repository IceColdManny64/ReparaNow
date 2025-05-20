package com.example.reparanow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.reparanow.ui.screens.LoginScreen
import com.example.reparanow.ui.screens.MainScreen
import com.example.reparanow.ui.screens.ProfileScreen
import com.example.reparanow.ui.screens.RegisterScreen
import com.example.reparanow.ui.theme.ReparaNowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ReparaNowTheme {
                ComposeMultiScreenApp()
                }
            }
        }
    }




@Composable
fun ComposeMultiScreenApp(){
    val navController = rememberNavController()
    SetupNavGraph(navController = navController)
}
@Composable
fun SetupNavGraph(navController: NavHostController){
    //startDestinations marks which screen is going to open at launch
    NavHost(navController = navController, startDestination = "loginScreen"){
        //add route name for every screen
        composable("loginScreen"){ LoginScreen(navController) }
        composable("registerScreen"){ RegisterScreen(navController) }
        composable("profScreen"){ ProfileScreen(navController) }
        composable("mainScreen"){ MainScreen(navController) }
    }
}
