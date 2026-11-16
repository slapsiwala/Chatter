package com.chatter

import io.ktor.client.plugins.websocket.*
import io.ktor.server.testing.*
import io.ktor.websocket.*
import kotlin.test.Test
import kotlin.test.assertEquals

class ChatSocketTest {

    @Test
    fun `message is broadcast to connected users`() = testApplication {
        application { rootModule() }
        val Client = createClient { install(WebSockets) }

        Client.webSocket("/chat") {
            send(Frame.Text("hello"))
            val reply = incoming.receive() as Frame.Text
            assertEquals("hello", reply.readText())
        }
    }
}