package com.example.medoblock.features.medicines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.util.EMPTY_STRING_ARRAY
import com.example.medoblock.core.utils.LoadingState
import com.example.medoblock.domain.models.Medicine
import com.example.medoblock.domain.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MedicineSate(
    val isLoading: LoadingState = LoadingState.IDLE,
    val medicines: List<Medicine> = emptyList()
)

@HiltViewModel
class MedicineVM @Inject constructor(
    private val apiRepository: ApiRepository
): ViewModel() {
    companion object{
        private const val TAG = "MedicineVM"
    }

    private val _state  = MutableStateFlow(MedicineSate())
    val state = _state.asStateFlow()

    init {
        getMedicine()
    }

    private fun getMedicine() = viewModelScope.launch {
        try {
            _state.emit(_state.value.copy(isLoading = LoadingState.LOADING))
            val result = apiRepository.getMedicines()
            _state.emit(_state.value.copy(isLoading = LoadingState.LOADED, medicines = result))
        }catch (e: Exception){
            _state.emit(_state.value.copy(isLoading = LoadingState.ERROR))
            e.printStackTrace()
        }
    }
}