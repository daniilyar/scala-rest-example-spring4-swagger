/**
 * @author Daniil Yaroslavtsev
 * Copyright 2015 by Confyrm Inc.
 */
package com.confyrm.eps.rest.api.config

import com.mangofactory.swagger.configuration.SpringSwaggerConfig
import com.mangofactory.swagger.models.dto.ApiInfo
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.{Bean, Configuration}

@Configuration
class SwaggerConfig {

  private val SWAGGER_GROUP: String = "confyrm-eps-api";
  private val DEFAULT_INCLUDE_PATTERNS: String = ".*event.*";

  @Bean
  @Autowired
  def swaggerMvcIntegrationSettings(springSwaggerConfig: SpringSwaggerConfig): SwaggerSpringMvcPlugin = {
    new SwaggerSpringMvcPlugin(springSwaggerConfig)
      .includePatterns(DEFAULT_INCLUDE_PATTERNS)
      .apiInfo(apiInfo())
      .swaggerGroup(SWAGGER_GROUP)
  }

  def apiInfo(): ApiInfo = {
    new ApiInfo(
      "Confyrm EPS API",
      "REST API for Confyrm Event Parsing Service",
      "<terms of service url>",
      "xxx@confyrm.com",
      "Confyrm EPS API Licence Type",
      "<API License URL>"
    )
  }

}
