package com.example.chatbot.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.chatbot.viewmodel.AuthViewModel
import com.example.chatbot.viewpage.ChatRoomListScreen
import com.example.chatbot.viewpage.ChatScreen
import com.example.chatbot.viewpage.SignInScreen
import com.example.chatbot.viewpage.SignUpScreen

@Composable
fun Navigation(
    authViewModel: AuthViewModel,
    navController: NavHostController
) {

    NavHost(navController = navController, startDestination = Screen.SignIn.route) {

        composable(Screen.SignIn.route) {
            SignInScreen(
                authViewModel = authViewModel,
                navigateToSignup = { navController.navigate(Screen.SignUp.route) }
            ) {
                navController.navigate(Screen.ChatroomListScreen.route)
            }
        }

        composable(Screen.SignUp.route) {
            SignUpScreen(authViewModel) {
                navController.navigate(Screen.SignIn.route)
            }
        }

        composable(Screen.ChatroomListScreen.route) {
            ChatRoomListScreen() {
                navController.navigate(Screen.ChatScreen.route + "/${it.id}")
            }
        }

        composable(Screen.ChatScreen.route + "/{roomId}") {
            val roomId: String = it.arguments?.getString("roomId") ?: ""
            Log.i("room ki id ", roomId)
            ChatScreen(roomId = roomId)
        }
    }
}