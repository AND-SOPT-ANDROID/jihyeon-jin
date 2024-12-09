package org.sopt.and.presentation.mypage.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.sopt.and.data.common.ErrorTypeWithMessage
import org.sopt.and.domain.model.entity.BaseResult
import org.sopt.and.domain.model.entity.GetMyHobbyResult
import org.sopt.and.domain.usecase.GetMyHobbyUseCase
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    private val getMyHobbyUseCase: GetMyHobbyUseCase
) : ViewModel() {
    private val _tokenInvalid = MutableSharedFlow<Boolean>()
    val tokenInvalid: SharedFlow<Boolean> = _tokenInvalid

    private val _myPageState = MutableStateFlow(GetMyHobbyResult(hobby = ""))
    val myPageState: StateFlow<GetMyHobbyResult> = _myPageState

    private val _errorMessageState = MutableStateFlow<String?>(null)
    val errorMessageState: StateFlow<String?> = _errorMessageState

    private val _isLogout = MutableSharedFlow<Boolean>()
    val isLogout: SharedFlow<Boolean> = _isLogout

    fun logout() {
        viewModelScope.launch {
            _isLogout.emit(true)
        }
    }

    fun getMyHobby(token: String) {
        viewModelScope.launch {
            when (val result = getMyHobbyUseCase(token)) {
                is BaseResult.Success -> {
                    _myPageState.value = result.data
                    _errorMessageState.value = null
                }
                is BaseResult.Error -> {
                    _errorMessageState.value = result.message
                    if (result.errorCode == ErrorTypeWithMessage.INVALID_TOKEN) {
                        _tokenInvalid.emit(true)
                    }
                }
            }
        }
    }
}