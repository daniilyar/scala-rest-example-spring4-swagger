package com.confyrm.eps.rest.api.interceptors

import java.io.UnsupportedEncodingException
import java.util.concurrent.atomic.AtomicLong
import javax.servlet.http.{HttpSession, HttpServletResponse, HttpServletRequest}

import com.github.isrsal.logging.{ResponseWrapper, RequestWrapper}
import com.google.common.base.Strings
import org.slf4j.{LoggerFactory, Logger}
import org.springframework.stereotype.Component
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter

@Component
class RequestLoggingInterceptor extends HandlerInterceptorAdapter {

  private val logger: Logger = LoggerFactory.getLogger(getClass)

  private val REQUEST_PREFIX: String = "Request: "
  private val RESPONSE_PREFIX: String = "Response: "
  private val id: AtomicLong = new AtomicLong(1)

  override def preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Object) = {
    val returnVal = super.preHandle(request, response, handler)
    id.incrementAndGet()
    returnVal
  }

  override def postHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Object, modelAndView: ModelAndView) = {
    super.postHandle(request, response, handler, modelAndView)

    logRequest(new RequestWrapper(id.get(), request))
    logResponse(new ResponseWrapper(id.get(), response))
  }

  def logRequest(request: RequestWrapper) = {

    val msg = new StringBuilder()
    msg.append(REQUEST_PREFIX)

    msg.append("request id=")
    msg.append(request.getId()).append("; ")

    val session = request.getSession(false)
    if (session != null) {
      msg.append("session id=").append(session.getId()).append("; ")
    }
    if (request.getContentType() != null) {
      msg.append("content type=").append(request.getContentType()).append("; ")
    }
    msg.append("uri=").append(request.getRequestURI())
    if (request.getQueryString() != null) {
      msg.append('?').append(request.getQueryString())
    }
    if (!isMultipart(request)) {
      try {
        var charEncoding: String = null
        if (request.getCharacterEncoding == null) {
          charEncoding = "UTF-8"
        } else {
          charEncoding = request.getCharacterEncoding()
        }
        val requestStr = new String(request.toByteArray(), request.getCharacterEncoding())
        if(!Strings.isNullOrEmpty(requestStr)) {
          msg.append("; payload=").append(requestStr)
        }
      } catch {
        case e: UnsupportedEncodingException =>
          logger.warn("Failed to parse request payload", e)
      }
    }
    logger.info(msg.toString())
  }

  def logResponse(response: ResponseWrapper) {
    val msg = new StringBuilder()
    msg.append(RESPONSE_PREFIX)
    msg.append("request id=").append((response.getId()))
    try {
      val responseStr = new String(response.toByteArray(), response.getCharacterEncoding())
      if(!Strings.isNullOrEmpty(responseStr)) {
        msg.append("; payload=").append(responseStr)
      }
    } catch {
      case e: UnsupportedEncodingException =>
        logger.warn("Failed to parse response payload", e)
    }
    logger.info(msg.toString())
  }

  private def isMultipart(request: HttpServletRequest): Boolean = {
    val result = (request.getContentType() != null && request.getContentType().startsWith("multipart/form-data"))
    result
  }

}
