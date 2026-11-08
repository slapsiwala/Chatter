package com.chatter

import io.ktor.server.application.*
import io.ktor.server.engine.*

fun main(args: Array<String>) {
    embeddedServer(
        factory = io.ktor.server.netty.Netty,
        port = 8080,
        host = "0.0.0.0",
        module = Application::rootModule
    ).start(wait = true)
}
