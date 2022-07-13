package com.example.springbootopenapitypescriptcodegen.config

import org.springframework.context.annotation.Configuration
import org.springframework.http.CacheControl
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import java.util.concurrent.TimeUnit


@Configuration
class WebConfig : WebMvcConfigurer {

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("/index.html")
            .addResourceLocations("classpath:/static/")
            .setCacheControl(CacheControl.noCache().cachePublic())
        registry.addResourceHandler("/assets/**")
            .addResourceLocations("classpath:/static/assets/")
            .setCacheControl(CacheControl.noCache().cachePublic())
        registry.addResourceHandler("/*.*.css", "/*.*.js")
            .addResourceLocations("classpath:/static/")
            .setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS).cachePublic())
        registry.addResourceHandler("swagger-ui.html")
            .addResourceLocations("classpath:/META-INF/resources/")
    }

    override fun addViewControllers(registry: ViewControllerRegistry) {
        registry.addViewController("/").setViewName("redirect:/index.html")
        registry.addViewController("/{x:[\\w-]+}").setViewName("forward:/index.html")
    }

}
