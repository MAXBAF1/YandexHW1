package com.example.yandexhw.main_screen.models

import android.graphics.Color
import org.json.JSONObject
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.UUID

data class Note(
    val title: String,
    val content: String,
    val importance: Importance,
    val uid: Uid = Uid(UUID.randomUUID().toString()),
    val color: Int = Color.WHITE,
    val createdAt: LocalDateTime = LocalDateTime.now()
) {
    companion object;

    override fun equals(other: Any?): Boolean {
        if (other !is Note) return false

        return this.uid == other.uid
    }

    override fun hashCode(): Int {
        var result = title.hashCode()
        result = 31 * result + content.hashCode()
        result = 31 * result + importance.hashCode()
        result = 31 * result + uid.hashCode()
        result = 31 * result + color
        result = 31 * result + createdAt.hashCode()
        return result
    }
}

fun Note.Companion.parse(json: JSONObject): Note? {
    return try {
        Note(
            uid = Uid(json.optString("uid", UUID.randomUUID().toString())),
            title = json.getString("title"),
            content = json.getString("content"),
            color = if (json.has("color")) json.getInt("color") else Color.WHITE,
            importance = Importance.valueOf(json.optString("importance", "NORMAL")),
            createdAt = LocalDateTime.parse(
                json.optString("createdAt", LocalDateTime.now().toString()),
                DateTimeFormatter.ISO_DATE_TIME
            )
        )
    } catch (e: Exception) {
        null
    }
}

val Note.json: JSONObject
    get() {
        val json = JSONObject()
        json.put("uid", uid.value)
        json.put("title", title)
        json.put("content", content)
        if (color != Color.WHITE) json.put("color", color)
        if (importance != Importance.NORMAL) json.put("importance", importance.name)
        json.put("createdAt", createdAt.format(DateTimeFormatter.ISO_DATE_TIME))
        return json
    }