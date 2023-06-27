package com.example.medoblock.features.supplyChainDetails

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medoblock.core.utils.LoadingState
import com.example.medoblock.domain.models.SupplyChain
import com.example.medoblock.domain.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

data class SupplyChainState(
    val loading: LoadingState = LoadingState.IDLE,
    val  supplyChain: SupplyChain? = null
)

@HiltViewModel
class SupplyChainVM @Inject constructor(
    private val apiRepository: ApiRepository
): ViewModel() {

    companion object{
        private const val TAG = "SupplyChainVM"
    }

    private val _state = MutableStateFlow(SupplyChainState())
    val state = _state.asStateFlow()

    fun getSupplyChain(address: String)= viewModelScope.launch{
        if(_state.value.loading == LoadingState.LOADING) return@launch
        try {
            _state.emit(_state.value.copy(loading = LoadingState.LOADING))
            val result = apiRepository.getSupplyChain("0xCE1004c045fA8b92beC67C12BC79dB87C2698C60")
            _state.emit(_state.value.copy(loading = LoadingState.LOADED, supplyChain = result))
            Log.d(TAG, "getSupplyChain: $result")
            //0xCE1004c045fA8b92beC67C12BC79dB87C2698C60
        }catch (e: Exception){
            _state.emit(_state.value.copy(loading = LoadingState.ERROR))
            e.printStackTrace()
        }
    }
}