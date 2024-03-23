package com.example.springbootopenapitypescriptcodegen

import com.example.springbootopenapitypescriptcodegen.model.Mood.*
import com.example.springbootopenapitypescriptcodegen.model.Person
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.MediaType.*
import org.springframework.web.client.RestClient
import org.springframework.web.util.DefaultUriBuilderFactory
import java.time.LocalDate


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
internal class PersonControllerIntegrationTest {

    @LocalServerPort
    private val port = 0

    lateinit var personControllerUrl: String
    lateinit var restClient: RestClient
    lateinit var testperson: Person
    lateinit var createResponse: Person

    @BeforeEach
    fun initRestClilent() {

        testperson = Person(null, "Peter", "foo", "Parker", LocalDate.now().minusYears(20), ENERGETIC)
        personControllerUrl = "http://localhost:$port/api/person"
        restClient = RestClient.builder().uriBuilderFactory(DefaultUriBuilderFactory(personControllerUrl)).build()

        createResponse = restClient.post() // or RestTemplate.postForEntity
            .contentType(APPLICATION_JSON)
            .body(testperson)
            .retrieve()
            .body(Person::class.java)!!
    }

    @Test
    fun `create and get person test`() {

        val getAllResponse = restClient.get()
            .retrieve()
            .body(Array<Person>::class.java)!!

        val getByIdResponse = restClient.get()
            .uri { it.pathSegment(createResponse.id.toString()).build() }
            .retrieve()
            .body(Person::class.java)!!

        Assertions.assertThat(createResponse == testperson.copy(id = createResponse.id))
        Assertions.assertThat(getAllResponse.isNotEmpty())
        Assertions.assertThat(getAllResponse.first().id == createResponse.id)
        Assertions.assertThat(getByIdResponse == testperson.copy(id = createResponse.id))

    }

    @Test
    fun `update person test`() {

        val changePerson = createResponse.copy(firstName = "Bruce", middleName = "bar", familyName = "Banner", dateOfBirth = LocalDate.now().minusYears(30), mood = ANGRY)
        val updateResponse = restClient.put() // or RestTemplate.postForEntity
            .contentType(APPLICATION_JSON)
            .body(changePerson)
            .retrieve()
            .body(Person::class.java)!!

        val getByIdResponse = restClient.get()
            .uri { it.pathSegment(createResponse.id.toString()).build() }
            .retrieve()
            .body(Person::class.java)!!

        Assertions.assertThat(updateResponse == changePerson)
        Assertions.assertThat(getByIdResponse == changePerson)

    }

    @Test
    fun `delete person test`() {

        val getByIdResponse = restClient.get()
            .uri { it.pathSegment(createResponse.id.toString()).build() }
            .retrieve()
            .body(Person::class.java)!!

        restClient.delete()
            .uri { it.pathSegment(createResponse.id.toString()).build() }
            .retrieve()

        val getAllResponse = restClient.get()
            .retrieve()
            .body(Array<Person>::class.java)!!

        Assertions.assertThat(createResponse == testperson.copy(id = createResponse.id))
        Assertions.assertThat(getByIdResponse.firstName == createResponse.firstName)
        Assertions.assertThat(getAllResponse.isEmpty())

    }
}