package com.example.yandexhw1.hw

import android.graphics.Color
import org.json.JSONObject
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.UUID

data class Note(
    val title: String,
    val content: String,
    val importance: Importance,
    val uid: String = UUID.randomUUID().toString(),
    val color: Int = Color.WHITE,
    val createdAt: LocalDateTime = LocalDateTime.now()
) {
    val json: JSONObject
        get() {
            val json = JSONObject()
            json.put("uid", uid)
            json.put("title", title)
            json.put("content", content)
            if (color != Color.WHITE) json.put("color", color)
            if (importance != Importance.NORMAL) json.put("importance", importance.name)
            json.put("createdAt", createdAt.format(DateTimeFormatter.ISO_DATE_TIME))
            return json
        }

    companion object {
        fun parse(json: JSONObject): Note? {
            return try {
                Note(
                    uid = json.optString("uid", UUID.randomUUID().toString()),
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
    }
}