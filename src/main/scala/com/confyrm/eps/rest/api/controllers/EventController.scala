/**
 * @author Daniil Yaroslavtsev
 * Copyright 2015 by Confyrm Inc.
 */
package com.confyrm.eps.rest.api.controllers

import java.lang.System.currentTimeMillis
import javax.servlet.http.HttpServletRequest

import com.confyrm.eps.broker.api.Broker
import com.confyrm.eps.common.serialization.JsonHelper
import com.confyrm.eps.model.event.RawEvent
import com.wordnik.swagger.annotations.ApiOperation
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.{HttpEntity, HttpStatus, ResponseEntity}
import org.springframework.web.bind.annotation._
import org.springframework.stereotype.Controller

@Controller
class EventController @Autowired()(private val broker: Broker) {

  private val log: Logger = LoggerFactory.getLogger(getClass)

  @ApiOperation(value = "List all people")
  @RequestMapping(value = Array("{providerId}/event"), method = Array(RequestMethod.POST))
  def processEventRequest(@PathVariable providerId: String, request: HttpServletRequest, requestEntity: HttpEntity[Array[Byte]]) = {

    log.info("Processing request for provider '{}' ...", providerId)

    //val rawEvent = RawEvent(currentTimeMillis(), request.getRemoteAddr, requestEntity.getBody, ContentType.A)
    //val confyrmEvent = broker.transform(rawEvent)

    new ResponseEntity[String]("", HttpStatus.OK);
  }

}