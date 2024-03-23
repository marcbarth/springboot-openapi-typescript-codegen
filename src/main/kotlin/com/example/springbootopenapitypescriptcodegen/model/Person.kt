package com.example.springbootopenapitypescriptcodegen.model

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate
import java.util.*


@Schema(name = "Person")
data class Person(
    val id: Long?,
    val firstName: String,
    val middleName: String? = null,
    val familyName: String,
    val dateOfBirth: LocalDate? = null,
    @Schema(enumAsRef = true)
    val mood: Mood? = null,
)