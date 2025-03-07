package com.example.yandexhw.main_screen.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.yandexhw.R
import com.example.yandexhw.main_screen.models.Importance
import com.example.yandexhw.main_screen.models.MainEvent
import com.example.yandexhw.main_screen.models.Note
import com.example.yandexhw.main_screen.presentation.MainViewModel
import com.example.yandexhw.main_screen.ui.views.CustomButton
import com.example.yandexhw.main_screen.ui.views.NoteList

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel(),
) {
    val viewState by viewModel.getViewState().collectAsStateWithLifecycle()

    val note = Note(
        title = "Моя заметка ${viewState.notes.size + 1}",
        content = "Содержимое заметки",
        importance = Importance.HIGH,
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        NoteList(modifier = Modifier.weight(1F), notes = viewState.notes)

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            CustomButton(text = stringResource(R.string.add)) {
                viewModel.obtainEvent(MainEvent.AddNote(note))
            }
            CustomButton(text = stringResource(R.string.remove)) {
                viewModel.obtainEvent(MainEvent.RemoveNote(viewState.notes.first().uid))
            }
            CustomButton(text = stringResource(R.string.clean)) {
                viewModel.obtainEvent(MainEvent.Clean)
            }
        }
    }
}