package com.example.springbootopenapitypescriptcodegen.controller

import com.example.springbootopenapitypescriptcodegen.model.Person
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/api")
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