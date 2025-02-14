package com.example.yandexhw1.hw

import java.io.File

fun main() {
    val file = File("notes.json")
    val notebook = FileNotebook(file)

    val note = Note(title = "Моя заметка", content = "Содержимое заметки", importance = Importance.HIGH)
    notebook.addNote(note)

    notebook.saveToFile()
    println("Заметка сохранена: ${note.json}")

    notebook.loadFromFile()
    println("Заметки после загрузки: ${notebook}")

    notebook.removeNote(note.uid)
    println("Заметка удалена")

    notebook.cleanupExpiredNotes(60)
}
