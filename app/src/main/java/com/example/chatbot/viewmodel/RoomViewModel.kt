package com.example.chatbot.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatbot.Injection
import com.example.chatbot.data.Result
import com.example.chatbot.data.RoomRepository
import com.example.chatbot.data.Room
import kotlinx.coroutines.launch

class RoomViewModel: ViewModel() {

    private val roomRepository: RoomRepository
    private val _rooms = MutableLiveData<List<Room>>()
    val rooms: MutableLiveData<List<Room>> = _rooms

    init {
        roomRepository = RoomRepository(Injection.instance())
        loadRooms()
    }

    fun createRoom(name: String) {
        viewModelScope.launch {
            roomRepository.createRoom(name)
            loadRooms()
        }
    }

    fun loadRooms() {
        viewModelScope.launch {
            when (val result = roomRepository.getRooms()) {
                is Result.Success -> _rooms.value = result.data
                is Result.Error -> { }
            }
        }
    }
}