package com.example.springbootopenapitypescriptcodegen.controller

import com.example.springbootopenapitypescriptcodegen.model.Gender
import com.example.springbootopenapitypescriptcodegen.model.Masculine
import com.example.springbootopenapitypescriptcodegen.model.Mood
import com.example.springbootopenapitypescriptcodegen.model.Person
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api", produces = [MediaType.APPLICATION_JSON_VALUE])
class PersonController {

    @GetMapping("/person/{id}")
    fun personById(@PathVariable id: Int): Person<out Gender> {
        val spiderman = Person(
            id = 1,
            firstName = "Peter",
            middleName = "Benjamin",
            familyName = "Parker",
            dateOfBirth = Date(),
            mood = Mood.BORED,
            moodYesterday = Mood.ENERGETIC,
            gender = Masculine("Normal","guinness")
        )
        spiderman.gender.drinkBeer()
        return spiderman
    }

    @GetMapping("/persons")
    fun persons(@RequestParam size: Int?): List<Person<out Gender>> {

        return listOf(
            Person(
                id = 1,
                firstName = "Peter",
                middleName = "Benjamin",
                familyName = "Parker",
                dateOfBirth = Date(),
                mood = Mood.BORED,
                moodYesterday = Mood.ENERGETIC,
                gender = Masculine("Normal")

            ),
            Person(
                id = 2,
                firstName = "Bruce",
                middleName = "Hulk",
                familyName = "Banner",
                dateOfBirth = Date(),
                mood = Mood.ANGRY,
                moodYesterday = Mood.CURIOUS,
                gender = Masculine("Extrem")

            )
        )
    }


}