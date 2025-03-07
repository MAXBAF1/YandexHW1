package com.example.yandexhw.main_screen.presentation

import com.example.yandexhw.core_presentation.BaseViewModel
import com.example.yandexhw.main_screen.models.FileNotebook
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.yandexhw.main_screen.models.MainAction
import com.example.yandexhw.main_screen.models.MainEvent
import com.example.yandexhw.main_screen.models.MainViewState
import com.example.yandexhw.main_screen.models.Note
import com.example.yandexhw.main_screen.models.Uid
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val notebook: FileNotebook,
) : BaseViewModel<MainViewState, MainEvent, MainAction>(
    initialState = MainViewState(notes = notebook.notes.toList())
) {

    override fun obtainEvent(viewEvent: MainEvent) {
        when (viewEvent) {
            is MainEvent.AddNote -> addNote(viewEvent.note)
            is MainEvent.RemoveNote -> removeNote(viewEvent.uid)
            MainEvent.Clean -> cleanNotes()
        }
    }

    private fun addNote(note: Note) {
        notebook.addNote(note)
        viewState.update { it.copy(notes = notebook.notes.toList()) }
    }

    private fun removeNote(uid: Uid) {
        notebook.removeNote(uid)
        viewState.update { it.copy(notes = notebook.notes.toList()) }
    }

    private fun cleanNotes() {
        notebook.cleanupExpiredNotes(60)
        viewState.update { it.copy(notes = notebook.notes.toList()) }
    }
}