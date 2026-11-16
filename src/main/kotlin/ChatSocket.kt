package com.chatter

import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import java.util.concurrent.ConcurrentHashMap
import kotlin.time.Duration.Companion.seconds

fun Application.configureSockets() {
    install(WebSockets) {
        pingPeriod = 15.seconds
        timeout = 15.seconds
    }

    // Every browser currently connected to the chat
    val connections = ConcurrentHashMap.newKeySet<DefaultWebSocketServerSession>()

    routing {
        webSocket("/chat") {
            connections += this
            try {
                for (frame in incoming) {
                    if (frame is Frame.Text) {
                        val text = frame.readText()
                        // Send the message to everyone connected
                        connections.forEach { it.send(text) }
                    }
                }
            } finally {
                connections -= this
            }
        }
    }
}