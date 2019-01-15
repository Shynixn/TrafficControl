package at.jku.controlsystem.service

import javax.json.Json
import javax.json.JsonObject
import javax.ws.rs.core.Response

class ResponseError{
    val message = "message"
    val code = "code"

    val unauthorized: JsonObject
        get() {
            val builder = Json.createObjectBuilder()
            builder.add(message, Response.Status.UNAUTHORIZED.reasonPhrase)
            builder.add(code, Response.Status.UNAUTHORIZED.statusCode)
            return builder.build()
        }

    val noResponse: JsonObject
        get(){
            val builder = Json.createObjectBuilder()
            builder.add(message, Response.Status.NOT_FOUND.reasonPhrase)
            builder.add(code, Response.Status.NOT_FOUND.statusCode)
            return builder.build()
        }
}