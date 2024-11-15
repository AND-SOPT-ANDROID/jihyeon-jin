package org.sopt.and.presentation.sign.state

data class SignUpState (
    val email: String = "",
    val password: String = "",
    val hobby: String = "",
    val isEmailValid : Boolean = false,
    val isPasswordValid : Boolean = false,
    val isHobbyValid : Boolean = false,
    val isEmailFieldFocused : Boolean = false,
    val isPasswordFieldFocused : Boolean = false,
    val isHobbyFieldFocused : Boolean = false,
    val isValid : Boolean = false
)