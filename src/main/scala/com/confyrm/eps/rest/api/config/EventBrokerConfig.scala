/**
 * @author Daniil Yaroslavtsev
 * Copyright 2015 by Confyrm Inc.
 */
package com.confyrm.eps.rest.api.config

import com.confyrm.eps.broker.BrokerImpl
import com.confyrm.eps.broker.api.Broker
import org.springframework.context.annotation.{Configuration, Bean}

@Configuration
class EventBrokerConfig {

  @Bean
  def eventBroker: Broker = {
    new BrokerImpl()
  }

}
