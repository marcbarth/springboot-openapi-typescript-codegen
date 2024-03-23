package com.example.springbootopenapitypescriptcodegen

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import io.swagger.v3.oas.annotations.servers.Server

@SpringBootApplication
@OpenAPIDefinition(servers = [Server(url= "localhost:4200", description = "Default Openapi Server URL")])
class SpringbootOpenapiTypescriptCodegenApplication

fun main(args: Array<String>) {
	runApplication<SpringbootOpenapiTypescriptCodegenApplication>(*args)
}
