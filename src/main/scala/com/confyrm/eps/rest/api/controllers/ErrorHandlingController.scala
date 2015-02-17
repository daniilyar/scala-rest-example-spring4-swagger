/**
 * @author Daniil Yaroslavtsev
 * Copyright 2015 by Confyrm Inc.
 */
package com.confyrm.eps.rest.api.controllers

import javax.servlet.http.{HttpServletRequest, HttpServletResponse}

import org.springframework.boot.autoconfigure.web.ErrorController
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class ErrorHandlingController extends ErrorController {

  @RequestMapping(value = Array("/error"))
  def error(request: HttpServletRequest, response: HttpServletResponse) = {
    response.sendError(response.getStatus);
  }

  override def getErrorPath(): String = {
    "/error"
  }
}
