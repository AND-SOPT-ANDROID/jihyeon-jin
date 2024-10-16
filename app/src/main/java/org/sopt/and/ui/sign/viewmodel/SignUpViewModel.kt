package org.sopt.and.ui.sign.viewmodel

import android.util.Patterns
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.sopt.and.ui.sign.state.SignUpState

class SignUpViewModel(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    val _signUpState = MutableStateFlow(SignUpState())
    val signUpState = _signUpState.asStateFlow()

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

    private fun updateIsValid() {
        _signUpState.update { currentState ->
            currentState.copy(isValid = _signUpState.value.isEmailValid && _signUpState.value.isPasswordValid)
        }
    }

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

    private val _signUpSuccess = MutableStateFlow(false)
    val signUpSuccess: StateFlow<Boolean> = _signUpSuccess

    fun signUp() {
        if (_signUpState.value.isValid) {
            _signUpSuccess.value = true
        } else {
            _signUpSuccess.value = false
        }
    }

    fun resetSignUpSuccess() {
        _signUpSuccess.value = false
    }
}
