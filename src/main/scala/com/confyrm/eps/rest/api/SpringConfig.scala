/**
 * @author Daniil Yaroslavtsev
 * Copyright 2015 by Confyrm Inc.
 */
package com.confyrm.eps.rest.api

import com.mangofactory.swagger.plugin.EnableSwagger
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.{ComponentScan, Configuration}
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@Configuration
@EnableAutoConfiguration
@EnableWebMvc
@EnableSwagger
@ComponentScan(basePackages = Array("com.confyrm.eps.rest.api"))
class SpringConfig {

}
