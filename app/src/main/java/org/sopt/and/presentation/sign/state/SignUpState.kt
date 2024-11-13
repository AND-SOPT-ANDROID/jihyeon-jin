package org.sopt.and.presentation.sign.state

data class SignUpState (
    val email: String = "",
    val password: String = "",
    val isEmailValid : Boolean = false,
    val isPasswordValid : Boolean = false,
    val isEmailFieldFocused : Boolean = false,
    val isPasswordFieldFocused : Boolean = false,
    val isValid : Boolean = false
)