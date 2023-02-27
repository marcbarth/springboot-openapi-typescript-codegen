package com.example.springbootopenapitypescriptcodegen.swagger

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.validation.annotation.Validated
import javax.validation.constraints.NotBlank


@Validated
@Configuration
@ConfigurationProperties(prefix = "openapi.spec")
class OpenApiConfigurationProperties {

    @NotBlank
    lateinit var folder: String

    @NotBlank
    lateinit var file: String

}