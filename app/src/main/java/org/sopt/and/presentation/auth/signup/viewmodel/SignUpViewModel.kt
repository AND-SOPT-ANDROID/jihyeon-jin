package org.sopt.and.presentation.auth.signup.viewmodel

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
import org.sopt.and.domain.model.entity.BaseResult
import org.sopt.and.domain.model.entity.UserData
import org.sopt.and.domain.model.entity.UserRegisterResult
import org.sopt.and.domain.usecase.RegisterUserUseCase
import org.sopt.and.presentation.auth.signup.SignUpState
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase
) : ViewModel() {
    private val _signUpState = MutableStateFlow(SignUpState())
    val signUpState = _signUpState.asStateFlow()

    private val _registerUserResultState = MutableStateFlow<UserRegisterResult?>(null)
    val registerUserResultState: StateFlow<UserRegisterResult?> = _registerUserResultState

    private val _errorMessageState = MutableStateFlow<String?>(null)
    val errorMessageState: StateFlow<String?> = _errorMessageState


    private val _signUpSuccess = MutableSharedFlow<Boolean>()
    val signUpSuccess: SharedFlow<Boolean> = _signUpSuccess

    fun updateUserName(newUserName: String) {
        _signUpState.update { currentState ->
            val isUserNameValid = validateUserName(newUserName)
            currentState.copy(
                username = newUserName,
                isUserNameValid = isUserNameValid
            )
        }
        updateIsValid()
    }

    fun updatePassword(newPassword: String) {
        _signUpState.update { currentState ->
            val isPasswordValid = validatePassword(newPassword)
            currentState.copy(
                password = newPassword,
                isPasswordValid = isPasswordValid
            )
        }
        updateIsValid()
    }

    fun updateHobby(newHobby: String) {
        _signUpState.update { currentState ->
            val isHobbyValid = validateHobby(newHobby)
            currentState.copy(
                hobby = newHobby,
                isHobbyValid = isHobbyValid
            )
        }
        updateIsValid()
    }

    fun updateUserNameFieldFocused(isFocused: Boolean) {
        _signUpState.update { currentState ->
            currentState.copy(isUserNameFieldFocused = isFocused)
        }
    }

    fun updatePasswordFieldFocused(isFocused: Boolean) {
        _signUpState.update { currentState ->
            currentState.copy(isPasswordFieldFocused = isFocused)
        }
    }

    fun updateHobbyFieldFocused(isFocused: Boolean) {
        _signUpState.update { currentState ->
            currentState.copy(isHobbyFieldFocused = isFocused)
        }
    }

    private fun updateIsValid() {
        _signUpState.update { currentState ->
            currentState.copy(
                isValid = _signUpState.value.isUserNameValid &&
                        _signUpState.value.isPasswordValid &&
                        _signUpState.value.isHobbyValid
            )
        }
    }

    private fun validateUserName(email: String): Boolean {
        return email.isNotBlank() && email.length <= 8
    }

    private fun validatePassword(password: String): Boolean {
        return password.isNotBlank() && password.length <= 8
    }

    private fun validateHobby(hobby: String): Boolean {
        return hobby.isNotBlank() && hobby.length <= 8
    }

    private suspend fun setSignUpSuccess(value: Boolean) {
        _signUpSuccess.emit(value)
    }

    fun registerUser() {
        viewModelScope.launch {
            when (val result = registerUserUseCase(
                UserData(
                    _signUpState.value.username,
                    _signUpState.value.password,
                    _signUpState.value.hobby
                )
            )
            ) {
                is BaseResult.Success -> {
                    _registerUserResultState.value = result.data
                    _errorMessageState.value = null
                    setSignUpSuccess(true)
                }

                is BaseResult.Error -> {
                    _registerUserResultState.value = null
                    _errorMessageState.value = result.message
                    setSignUpSuccess(false)
                }
            }
        }
    }

    fun resetSignUpSuccess() {
        viewModelScope.launch {
            setSignUpSuccess(false)
        }
    }
}
