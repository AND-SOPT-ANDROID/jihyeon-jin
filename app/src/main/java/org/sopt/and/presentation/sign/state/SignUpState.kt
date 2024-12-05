package org.sopt.and.presentation.sign.state

data class SignUpState (
    val username: String = "",
    val password: String = "",
    val hobby: String = "",
    val isUserNameValid : Boolean = false,
    val isPasswordValid : Boolean = false,
    val isHobbyValid : Boolean = false,
    val isUserNameFieldFocused : Boolean = false,
    val isPasswordFieldFocused : Boolean = false,
    val isHobbyFieldFocused : Boolean = false,
    val isValid : Boolean = false
)