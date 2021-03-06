package com.example.springbootopenapitypescriptcodegen.swagger

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.annotation.Configuration
import org.springframework.context.event.EventListener
import org.springframework.web.client.RestTemplate
import java.io.File

@Configuration
class SwaggerLoadApiDocConfiguration {

    private val logger = LoggerFactory.getLogger(SwaggerLoadApiDocConfiguration::class.java)

    @Value("\${server.port:8080}")
    private val serverport: String? = null

    @EventListener(ApplicationReadyEvent::class)
    fun loadSwaggerDoc() {
        val restTemplate = RestTemplate()
        try {
            val apiDoc = restTemplate.getForObject("http://localhost:$serverport/v3/api-docs", String::class.java)
            if (!File("swagger").exists()) File("swagger").mkdirs()
            File("swagger/api-docs.json").writeBytes(apiDoc!!.toByteArray())
            logger.info("saved Swagger API-Doc under /swagger/api-docs.json")
        } catch (ex: Exception) {
            logger.warn("Swagger API-Doc could not be read!")
        }
    }


}
