package com.example.yandexhw.main_screen.models

import com.example.yandexhw.main_screen.utils.MainConstant
import org.json.JSONArray
import org.json.JSONObject
import org.slf4j.LoggerFactory
import java.io.File
import java.time.LocalDateTime


class FileNotebook(private val file: File) {
    private val _notes = mutableSetOf<Note>()
    val notes: Set<Note> = _notes

    private val logger = LoggerFactory.getLogger(MainConstant.LOGGER_NAME)

    init {
        loadFromFile()
    }

    fun addNote(note: Note): Boolean {
        logger.info("addNote $note")

        val added = _notes.add(note)
        if (added) saveToFile()

        return added
    }

    fun removeNote(uid: Uid): Boolean {
        logger.info("removeNote $uid")

        val removed = _notes.removeIf { it.uid.value == uid.value }
        if (removed) saveToFile()

        return removed
    }

    private fun saveToFile() {
        logger.info("saveToFile ${_notes.size} $_notes")

        val jsonArray = JSONArray()
        _notes.forEach { jsonArray.put(it.json) }

        val jsonObject = JSONObject().put("notes", jsonArray)
        file.writeText(jsonObject.toString())
    }

    private fun loadFromFile() {
        if (!file.exists()) return

        val jsonData = file.readText()
        val jsonObject = JSONObject(jsonData)
        val jsonNotes = jsonObject.getJSONArray("notes")

        logger.info("loadFromFile ${jsonNotes.length()} $jsonNotes")

        _notes.clear()
        for (i in 0 until jsonNotes.length()) {
            Note.parse(jsonNotes.getJSONObject(i))?.let { _notes.add(it) }
        }
    }

    fun cleanupExpiredNotes(expirationMinutes: Long) {
        val now = LocalDateTime.now()
        val removed = _notes.removeIf { it.createdAt.plusMinutes(expirationMinutes).isBefore(now) }

        if (removed) {
            logger.info("cleanupExpiredNotes")
            saveToFile()
        }
    }
}
