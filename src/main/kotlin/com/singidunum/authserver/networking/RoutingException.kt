package com.singidunum.authserver.networking

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException
import java.lang.IllegalStateException

sealed class RoutingException(message: String, cause: Throwable, status: HttpStatus = HttpStatus.BAD_REQUEST) :
    ResponseStatusException(status, message, cause)

class RoutingEndpointException(message: String) : RoutingException(message, IllegalStateException())
class RoutingApiCallException(message: String, cause: Throwable) : RoutingException(message, cause)
class AuthorizationException(message: String): RoutingException(message, IllegalStateException(), HttpStatus.UNAUTHORIZED)