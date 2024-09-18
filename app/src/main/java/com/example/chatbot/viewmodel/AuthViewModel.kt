package com.example.chatbot.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatbot.Injection
import com.example.chatbot.data.UserRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import com.example.chatbot.data.Result

class AuthViewModel: ViewModel() {

    private val userRepository: UserRepository
    private val _authResult = MutableLiveData<Result<Boolean>>()

    val authResult: LiveData<Result<Boolean>> = _authResult

    init {
        userRepository = UserRepository(
            FirebaseAuth.getInstance(),
            Injection.instance()
        )
    }

    fun signUp(email: String, password: String, firstName: String, lastName: String) {
        viewModelScope.launch {
            _authResult.value = userRepository.signUp(email, password, firstName, lastName)
        }
    }

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            _authResult.value = userRepository.signIn(email, password)
        }
    }
}