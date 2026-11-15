package com.chatter

import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        // Quick check that the server is up
        get("/health") {
            call.respondText("OK")
        }

        // Serves the frontend files from resources/static at the root URL
        staticResources("/", "static")
    }
}