package com.example.medoblock.features.chat

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medoblock.core.utils.LoadingState
import com.example.medoblock.domain.models.Message
import com.example.medoblock.domain.repository.ApiRepository
import com.example.medoblock.features.shared.utils.MDateTime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val apiRepository: ApiRepository
): ViewModel() {

    var messages by mutableStateOf<List<Message>>(emptyList())
        private set

    private val _responseLoading = MutableStateFlow(LoadingState.IDLE)
    val responseLoading = _responseLoading.asStateFlow()


     fun addMessage()  = viewModelScope.launch{
        _responseLoading.emit(LoadingState.LOADING)
        runBlocking {
            delay(100)
        }

        Log.d("TAG", "addMessage: ${responseLoading.value}")
        _responseLoading.emit(LoadingState.LOADED)

        val botWelMsg1 = Message("Welcome to MedoBlock 🙌", System.currentTimeMillis(), true)
        val botWelMsg2 = Message("How can I help you?", System.currentTimeMillis(), true)
        val newMessage = Message("How are you?", 1687368328, false)
        val newMessage2 = Message("How can a medicine help to cure illness ?", 1687368328, false)
        val newMessage3 = Message("How can a  to cure illness ?", 1687368328, false)
        val newMessage4 = Message("medicine help to cure illness ?", 1687368328, false)
        val newMessage5 = Message("How can a medicine help to cure illness ?", 1687368328, false)
        val botMsg1 = Message("How can I help you ?", 1687368328, true)
        val botMsg2 = Message("Medicines hepls to improve illnesss there are many scientific  ?", 1687368328, true)
        val botMsg3 = Message("yes sure", 1687368328, true)
        val botMsg4 = Message("How can I help you ?", 1687368328, true)
        val botMsg5 = Message("Happy to help?", 1687368328, true)

        val mutableMsg = messages.toMutableList()
        mutableMsg.add(botWelMsg1)
        mutableMsg.add(botWelMsg2)
//        mutableMsg.add(newMessage2)
//        mutableMsg.add(botMsg2)
//        mutableMsg.add(botMsg3)
//        mutableMsg.add(newMessage3)
//        mutableMsg.add(botMsg4)
//        mutableMsg.add(newMessage4)
//        mutableMsg.add(newMessage5)
//        mutableMsg.add(botMsg5)
//
//
//        mutableMsg.add(botMsg1)
//        mutableMsg.add(newMessage)
//        mutableMsg.add(newMessage2)
//        mutableMsg.add(botMsg2)
//        mutableMsg.add(botMsg3)
//        mutableMsg.add(newMessage3)
//        mutableMsg.add(botMsg4)
//        mutableMsg.add(newMessage4)
//        mutableMsg.add(newMessage5)
//        mutableMsg.add(botMsg5)

        messages = mutableMsg
    }

    fun addUserMessage(message: String) = viewModelScope.launch{
        val newMsg = Message(message,System.currentTimeMillis(), false)
        val mutableMsg = messages.toMutableList()
        mutableMsg.add(newMsg)
        messages = mutableMsg

        _responseLoading.emit(LoadingState.LOADING)
        try {
            val response = apiRepository.sendChatQuery(message).trim()
            val newResMsg = Message(response,System.currentTimeMillis(), true)
            val mutableResMsg = messages.toMutableList()
            mutableResMsg.add(newResMsg)
            messages = mutableResMsg
            _responseLoading.emit(LoadingState.LOADED)
        }catch (e: Exception){
            _responseLoading.emit(LoadingState.ERROR)
            e.printStackTrace()
        }
    }
}