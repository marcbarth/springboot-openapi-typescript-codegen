package com.example.springbootopenapitypescriptcodegen.swagger

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.servers.Server
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.annotation.Configuration
import org.springframework.context.event.EventListener
import org.springframework.web.client.RestTemplate
import java.io.File

@Configuration
class SwaggerLoadApiDocConfiguration(private val configurationProps: OpenApiConfigurationProperties) {

    private val logger = LoggerFactory.getLogger(SwaggerLoadApiDocConfiguration::class.java)

    @Value("\${server.port:8080}")
    private val serverport: String? = null

    @EventListener(ApplicationReadyEvent::class)
    fun loadSwaggerDoc() {
        try {
            val apiDoc = RestTemplate().getForObject("http://localhost:$serverport/v3/api-docs", String::class.java)
            if (!File(configurationProps.folder).exists()) File(configurationProps.folder).mkdirs()
            File("${configurationProps.folder}/${configurationProps.file}").writeBytes(apiDoc!!.toByteArray())
            logger.info("saved Swagger API-Doc under ${configurationProps.folder}/${configurationProps.file}")
        } catch (ex: Exception) {
            logger.warn("Swagger API-Doc could not be read!")
        }
    }


}
