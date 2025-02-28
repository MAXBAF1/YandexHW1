package com.example.yandexhw1.hw

import org.json.JSONObject
import java.io.File
import java.time.LocalDateTime

class FileNotebook(private val file: File) {
    private val notes = mutableSetOf<Note>()

    fun addNote(note: Note): Boolean = notes.add(note)

    fun removeNote(uid: Uid): Boolean = notes.removeAll { it.uid == uid }

    fun saveToFile() {
        file.writeText(JSONObject().put("notes", notes.map { it.json }).toString())
    }

    fun loadFromFile() {
        if (!file.exists()) return
        val jsonData = file.readText()
        val jsonObject = JSONObject(jsonData)
        val jsonNotes = jsonObject.getJSONArray("notes")
        notes.clear()
        for (i in 0 until jsonNotes.length()) {
            Note.parse(jsonNotes.getJSONObject(i))?.let { notes.add(it) }
        }
    }

    fun cleanupExpiredNotes(expirationMinutes: Long) {
        val now = LocalDateTime.now()
        notes.removeIf { it.createdAt.plusMinutes(expirationMinutes).isBefore(now) }
    }
}
