package com.example.springbootopenapitypescriptcodegen.swagger

import jakarta.validation.constraints.NotBlank
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.validation.annotation.Validated


@Validated
@Configuration
@ConfigurationProperties(prefix = "openapi.spec")
class OpenApiConfigurationProperties {

    @NotBlank
    lateinit var folder: String

    @NotBlank
    lateinit var file: String

}