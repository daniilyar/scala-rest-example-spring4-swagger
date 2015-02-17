/**
 * @author Daniil Yaroslavtsev
 * Copyright 2015 by Confyrm Inc.
 */
package com.confyrm.eps.rest.api

import org.springframework.boot.SpringApplication

object Runner {

  def main (args: Array[String]) {
    SpringApplication.run(classOf[SpringConfig]);
  }

}