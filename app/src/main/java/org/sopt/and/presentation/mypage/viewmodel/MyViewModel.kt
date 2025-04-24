package org.sopt.and.presentation.mypage.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.and.core.utils.PreferenceUtil
import org.sopt.and.data.common.ErrorTypeWithMessage
import org.sopt.and.domain.model.entity.BaseResult
import org.sopt.and.domain.usecase.GetMyHobbyUseCase
import org.sopt.and.presentation.util.BaseViewModel
import org.sopt.and.presentation.mypage.MyPageContract.MyPageUiEffect
import org.sopt.and.presentation.mypage.MyPageContract.MyPageUiEvent
import org.sopt.and.presentation.mypage.MyPageContract.MyPageUiState
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    private val getMyHobbyUseCase: GetMyHobbyUseCase,
    private val preferenceUtil: PreferenceUtil
) : BaseViewModel<MyPageUiState, MyPageUiEvent, MyPageUiEffect>(MyPageUiState()) {
    override fun reduceState(event: MyPageUiEvent) {
        when (event) {
            is MyPageUiEvent.LoadHobby -> {
                loadHobby()
            }

            MyPageUiEvent.Logout -> {
                logout()
            }
        }
    }

    private fun loadHobby() {
        val token = preferenceUtil.getUserToken()
        Log.d("my**", token.toString())
        if (token.isNullOrEmpty()) {
            updateState(
                currentState.copy(
                    tokenInvalid = true
                )
            )
            postEffect(MyPageUiEffect.NavigateToSignIn)
            return
        }
        updateState(currentState.copy(isLoading = true))
        viewModelScope.launch {
            when (val result = getMyHobbyUseCase(token)) {
                is BaseResult.Success -> {
                    updateState(
                        currentState.copy(
                            hobby = result.data.hobby,
                            isLoading = false,
                            errorMessage = null,
                            tokenInvalid = false
                        )
                    )
                }

                is BaseResult.Error -> {
                    updateState(
                        currentState.copy(
                            isLoading = false,
                            errorMessage = result.message
                        )
                    )
                    if (result.errorCode == ErrorTypeWithMessage.INVALID_TOKEN) {
                        preferenceUtil.clearUserToken()
                        updateState(
                            currentState.copy(
                                tokenInvalid = true
                            )
                        )
                        postEffect(MyPageUiEffect.NavigateToSignIn)
                    } else {
                        postEffect(MyPageUiEffect.ShowErrorSnackBar(result.message))
                    }
                }
            }
        }
    }

    private fun logout() {
        preferenceUtil.clearUserToken()
        updateState(currentState.copy(isLoggedOut = true))
        postEffect(MyPageUiEffect.NavigateToSignIn)
    }
}