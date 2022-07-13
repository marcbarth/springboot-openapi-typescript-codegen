package com.example.springbootopenapitypescriptcodegen.controller

import com.example.springbootopenapitypescriptcodegen.model.Person
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api", produces = [MediaType.APPLICATION_JSON_VALUE])
class PersonController {

    @GetMapping("/person/{id}")
    fun personById(@PathVariable id: Int) =
        Person(id = 1, firstName = "Bruce", familyName = "Banner", dateOfBirth = Date())

    @GetMapping("/persons")
    fun persons(@RequestParam size: Int?) =
        listOf(
            Person(id = 1, firstName = "Bruce", familyName = "Banner", dateOfBirth = Date()),
            Person(id = 1, firstName = "Peter", middleName = "Benjamin", familyName = "Parker", dateOfBirth = Date())
        )

}