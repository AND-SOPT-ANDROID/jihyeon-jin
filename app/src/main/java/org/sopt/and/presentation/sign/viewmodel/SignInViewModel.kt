package org.sopt.and.presentation.sign.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.sopt.and.presentation.sign.state.SignInState
import org.sopt.and.domain.model.entity.BaseResult
import org.sopt.and.domain.model.entity.UserData
import org.sopt.and.domain.model.entity.UserLoginResult
import org.sopt.and.domain.usecase.LoginUseCase
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {
    val _signInState = MutableStateFlow(SignInState())
    val signInState = _signInState.asStateFlow()

    private val _loginUserResultState = MutableStateFlow<UserLoginResult?>(null)
    val loginUserResultState: StateFlow<UserLoginResult?> = _loginUserResultState

    private val _errorMessageState = MutableStateFlow<String?>(null)
    val errorMessageState: StateFlow<String?> = _errorMessageState

    private val _signInSuccess =  MutableSharedFlow<Boolean>()
    val signInSuccess: SharedFlow<Boolean> = _signInSuccess

    fun updateUserName(newUserName: String) {
        _signInState.update { currentState ->
            currentState.copy(
                username = newUserName
            )
        }
    }
    fun updatePassword(newPassword: String) {
        _signInState.update { currentState ->
            currentState.copy(
                password = newPassword
            )
        }
    }

    private suspend fun setSignInSuccess(value: Boolean) {
        _signInSuccess.emit(value)
    }

    fun signIn() {
        viewModelScope.launch {
            when (val result = loginUseCase(
                UserData(
                    _signInState.value.username,
                    _signInState.value.password,
                    ""
                )
            )
            ) {
                is BaseResult.Success -> {
                    _loginUserResultState.value = result.data
                    _errorMessageState.value = null
                    setSignInSuccess(true)
                }
                is BaseResult.Error -> {
                    _loginUserResultState.value = null
                    _errorMessageState.value = result.message
                    setSignInSuccess(false)
                }
            }
        }
    }

    suspend fun resetSignInSuccess() {
        setSignInSuccess(false)
    }
}
