package org.sopt.and.presentation.sign.state

data class SignInState (
    val email: String = "",
    val password: String = "",
    val snackbarMessage : String? = null,
    val isValid : Boolean = false
)