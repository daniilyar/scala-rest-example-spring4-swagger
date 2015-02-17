/**
 * @author Daniil Yaroslavtsev
 * Copyright 2015 by Confyrm Inc.
 */
package com.confyrm.eps.rest.api.config

import com.confyrm.eps.rest.api.interceptors.RequestLoggingInterceptor
import org.springframework.context.annotation.{Configuration, Bean}
import org.springframework.web.servlet.config.annotation.{InterceptorRegistry, WebMvcConfigurerAdapter}

@Configuration
class InterceptorsConfig {

  @Bean
  def mvcConfig: WebMvcConfigurerAdapter = {
    new WebMvcConfigurerAdapter() {
      override def addInterceptors(registry: InterceptorRegistry) = {
        registry.addInterceptor(new RequestLoggingInterceptor)
      }
    }
  }

}
