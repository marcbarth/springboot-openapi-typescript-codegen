package com.example.springbootopenapitypescriptcodegen.controller

import com.example.springbootopenapitypescriptcodegen.data.PersonsRepository
import com.example.springbootopenapitypescriptcodegen.model.Person
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/person")
class PersonController {

    val personRepo = PersonsRepository.persons

    @GetMapping()
    fun getAllPerson(): List<Person> {
        return personRepo
    }

    @GetMapping("/{id}")
    fun getPersonById(@PathVariable id: Long): Person {
        return personRepo.find { it.id == id } ?: throw Exception("Person not found")
    }

    @PostMapping
    fun createPerson(@RequestBody person: Person): Person {
        val nextId = (personRepo.maxByOrNull { it.id!! }?.id ?: 0) + 1
        val personWithId = person.copy(id = nextId)
        personRepo.add(personWithId)
        return personWithId
    }

    @PutMapping
    fun changePerson(@RequestBody person: Person): Person {
        val persistentPerson = personRepo.find { it.id == person.id } ?: throw Exception("Person not found")
        personRepo.remove(persistentPerson)
        personRepo.add(person)
        return person
    }

    @DeleteMapping("/{id}")
    fun deletePerson(@PathVariable id: Long) {
        val persistentPerson = personRepo.find { it.id == id } ?: throw Exception("Person not found")
        personRepo.remove(persistentPerson)
    }

}