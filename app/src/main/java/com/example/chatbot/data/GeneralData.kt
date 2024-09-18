package com.example.chatbot.data

data class Room(
    val id: String = "",
    val name: String = ""
)

data class Message(
    val text: String = "",
    val senderId: String = "",
    val senderFirstName: String = "",
    val isSentByCurrentUser: Boolean = false,
    val timestamp: Long = System.currentTimeMillis()
)
