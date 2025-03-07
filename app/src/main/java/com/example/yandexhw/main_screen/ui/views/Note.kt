package com.example.yandexhw.main_screen.ui.views

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import com.example.yandexhw.main_screen.models.Note

@Composable
fun Note(note: Note, modifier: Modifier = Modifier) {
    Text(
        modifier = modifier,
        text = note.title,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
    )
}