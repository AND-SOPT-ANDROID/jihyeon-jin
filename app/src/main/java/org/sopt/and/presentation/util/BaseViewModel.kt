package org.sopt.and.presentation.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

interface UiState
interface UiEvent
interface UiEffect

abstract class BaseViewModel<State: UiState, Event: UiEvent, Effect: UiEffect>(
    initialState: State
) : ViewModel() {
    private val _uiState: MutableStateFlow<State> = MutableStateFlow(initialState)
    val currentState: State
        get() = _uiState.value

    val uiState = _uiState.asStateFlow()

    private val _event: MutableSharedFlow<Event> = MutableSharedFlow()
    val event = _event.asSharedFlow()

    private val _effect: Channel<Effect> = Channel()
    val effect = _effect.receiveAsFlow()

    init {
        subscribeEvents()
    }

    protected abstract fun reduceState(event: Event)
    protected fun postEffect(effect: Effect) {
        viewModelScope.launch {
            _effect.send(effect)
        }
    }

    private fun subscribeEvents() {
        viewModelScope.launch {
            event.collect {
                reduceState(it)
            }
        }
    }

    protected fun updateState(currentState: State) {
        _uiState.update {
            currentState
        }
    }

    fun sendEvent(event: Event) {
        viewModelScope.launch { _event.emit(event) }
    }

}