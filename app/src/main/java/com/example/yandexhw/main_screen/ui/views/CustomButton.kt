package com.example.yandexhw.main_screen.ui.views

import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CustomButton(text: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    ElevatedButton(modifier = modifier, onClick = onClick) {
        Text(text = text)
    }
}