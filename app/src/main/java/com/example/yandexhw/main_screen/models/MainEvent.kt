package com.example.yandexhw.main_screen.models


sealed class MainEvent {
    data class AddNote(val note: Note) : MainEvent()
    data class RemoveNote(val uid: Uid) : MainEvent()
    data object Clean : MainEvent()
}