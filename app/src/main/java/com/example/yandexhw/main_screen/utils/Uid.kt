package com.example.yandexhw.main_screen.models

import java.util.UUID

@JvmInline
value class Uid(val value: String = UUID.randomUUID().toString())