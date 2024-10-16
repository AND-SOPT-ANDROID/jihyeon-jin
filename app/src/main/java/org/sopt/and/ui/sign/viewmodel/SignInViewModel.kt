package org.sopt.and.ui.sign.viewmodel

import android.content.Context
import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.sopt.and.R
import org.sopt.and.ui.sign.state.SignInState
import org.sopt.and.utils.PreferenceUtils

class SignInViewModel(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    val _signInState = MutableStateFlow(SignInState())
    val signInState = _signInState.asStateFlow()

    private val _signInSuccess = MutableStateFlow(false)
    val signInSuccess: StateFlow<Boolean> = _signInSuccess

    fun updateEmail(newEmail: String) {
        _signInState.update { currentState ->
            currentState.copy(
                email = newEmail
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

    private fun updateSnackbarMessage(message: String?) {
        _signInState.update { currentState ->
            currentState.copy(
                snackbarMessage = message
            )
        }
    }

    fun updateIsValid(emailInput: String, passwordInput: String) {
        val isEqual = (_signInState.value.email === emailInput) && (_signInState.value.password === passwordInput)
        val isNotBlank = emailInput.isNotBlank() && passwordInput.isNotBlank()
        _signInState.update { currentState ->
            currentState.copy(
                isValid = (isEqual && isNotBlank)
            )
        }
    }

    fun signIn(context: Context, emailInput: String, passwordInput: String) {
        if (_signInState.value.email == emailInput && _signInState.value.password == passwordInput) {
            if (emailInput.isNotBlank() && passwordInput.isNotBlank()) {
                PreferenceUtils.saveUserId(context, emailInput)
                PreferenceUtils.saveUserPassword(context, passwordInput)
                _signInSuccess.value = true
                updateSnackbarMessage(context.getString(R.string.sign_in_snackbar_login_success))
            } else {
                updateSnackbarMessage(context.getString(R.string.sign_in_snackbar_enter_id_password))
            }
        } else {
            updateSnackbarMessage(context.getString(R.string.sign_in_snackbar_error_id_pw))
        }
    }

    fun clearSnackbarMessage() {
        updateSnackbarMessage(null)
    }

    fun resetSignInSuccess() {
        _signInSuccess.value = false
    }
}
