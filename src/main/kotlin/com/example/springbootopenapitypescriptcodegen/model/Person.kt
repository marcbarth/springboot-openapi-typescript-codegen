package com.example.springbootopenapitypescriptcodegen.model

import io.swagger.v3.oas.annotations.media.Schema
import java.util.*

class Person(
    val id: Int,
    val firstName: String,
    val middleName: String? = null,
    val familyName: String,
    val dateOfBirth: Date,
    @Schema(enumAsRef = true)
    val mood: Mood,
    @Schema(enumAsRef = true)
    val moodYesterday: Mood
)