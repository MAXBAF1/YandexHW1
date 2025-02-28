package com.example.yandexhw1.hw

import java.util.UUID

@JvmInline
value class Uid(val value: String = UUID.randomUUID().toString())