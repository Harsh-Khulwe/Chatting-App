package com.example.chatbot.navigation

sealed class Screen(val route: String) {

    object SignIn: Screen("signin")
    object SignUp: Screen("signup")
    object ChatroomListScreen: Screen("chatroom_screen")
    object ChatScreen: Screen("chat_screen")
}

