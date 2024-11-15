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
import org.sopt.and.data.model.BaseResponse
import org.sopt.and.domain.model.BaseResult
import org.sopt.and.domain.model.UserData
import org.sopt.and.domain.model.UserRegisterResult
import org.sopt.and.domain.usecase.RegisterUserUseCase
import org.sopt.and.presentation.sign.state.SignUpState
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase
) : ViewModel() {
    private val _signUpState = MutableStateFlow(SignUpState())
    val signUpState = _signUpState.asStateFlow()

    private val _userResultState = MutableStateFlow<UserRegisterResult?>(null)
    val userResultState: StateFlow<UserRegisterResult?> = _userResultState

    private val _errorMessageState = MutableStateFlow<String?>(null)
    val errorMessageState: StateFlow<String?> = _errorMessageState

    fun updateEmail(newEmail: String) {
        _signUpState.update { currentState ->
            val isEmailValid = validateEmail(newEmail)
            currentState.copy(
                email = newEmail,
                isEmailValid = isEmailValid
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

    fun updateEmailFieldFocused(isFocused: Boolean) {
        _signUpState.update { currentState ->
            currentState.copy(isEmailFieldFocused = isFocused)
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
                isValid = _signUpState.value.isEmailValid &&
                    _signUpState.value.isPasswordValid &&
                    _signUpState.value.isHobbyValid
            )
        }
    }

    /* 기존 wavve 제약사항
    private fun validateEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    private fun validatePassword(password: String): Boolean {
        val hasUpperCase = password.any { it.isUpperCase() }
        val hasLowerCase = password.any { it.isLowerCase() }
        val hasDigit = password.any { it.isDigit() }
        val hasSpecialChar = password.any { !it.isLetterOrDigit() }
        val lengthValid = password.length in 8..20
        val complexityValid = listOf(hasUpperCase, hasLowerCase, hasDigit, hasSpecialChar).count { it } >= 3
        return lengthValid && complexityValid
    }
    */

    //과제 기능 명세에 따른 제약사항, 공통 기능이지만 제약사항 변경 시를 대비해 각각 함수 분리
    private fun validateEmail(email: String): Boolean {
        return email.isNotBlank() && email.length <= 8
    }
    private fun validatePassword(password: String): Boolean {
        return password.isNotBlank() && password.length <= 8
    }

    private fun validateHobby(hobby: String): Boolean {
        return hobby.isNotBlank() && hobby.length <= 8
    }

    private val _signUpSuccess = MutableSharedFlow<Boolean>()
    val signUpSuccess: SharedFlow<Boolean> = _signUpSuccess

    private suspend fun setSignUpSuccess(value: Boolean) {
        _signUpSuccess.emit(value)
    }
    fun registerUser() {
        viewModelScope.launch {
            when (val result = registerUserUseCase(
                UserData(
                _signUpState.value.email,
                _signUpState.value.password,
                _signUpState.value.hobby
                ))
            ) {
                is BaseResult.Success -> {
                    _userResultState.value = result.data
                    _errorMessageState.value = null
                    setSignUpSuccess(true)
                }
                is BaseResult.Error -> {
                    _userResultState.value = null
                    _errorMessageState.value = result.message
                    setSignUpSuccess(false)
                }
            }
        }
    }

    suspend fun resetSignUpSuccess() {
        setSignUpSuccess(false)
    }
}
