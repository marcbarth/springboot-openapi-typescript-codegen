package com.example.springbootopenapitypescriptcodegen.model

import java.util.*

class Person(
    val id: Int,
    val firstName: String,
    val middleName: String? = null,
    val familyName: String,
    val dateOfBirth: Date
)