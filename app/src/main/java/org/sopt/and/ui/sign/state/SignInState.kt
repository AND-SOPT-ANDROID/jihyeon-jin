package org.sopt.and.ui.sign.state

data class SignInState (
    val email: String = "",
    val password: String = "",
    val snackbarMessage : String? = null,
    val isValid : Boolean = false
)