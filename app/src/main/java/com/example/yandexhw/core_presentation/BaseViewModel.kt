package com.example.yandexhw.core_presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow

abstract class BaseViewModel<State, Event, Action>(
    initialState: State,
) : ViewModel() {
    protected val viewState = MutableStateFlow(initialState)
    protected val actionsChannel = Channel<Action>()

    fun getViewState(): StateFlow<State> = viewState
    fun getActions(): Flow<Action> = actionsChannel.receiveAsFlow()

    open fun obtainEvent(viewEvent: Event) {}
}