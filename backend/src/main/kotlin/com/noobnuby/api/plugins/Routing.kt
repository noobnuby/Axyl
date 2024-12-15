package com.noobnuby.api.plugins

import com.noobnuby.api.data.PostData
import io.ktor.http.*
import io.ktor.serialization.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText(
				contentType = ContentType.parse("text/html"),
				text = """
					<h1> axyl api service
				""".trimIndent()
			)
        }

		//TODO : Check Valid URL
		//TODO : Add DataBase
		//TODO : Key Logic
		post("/url") {
			try {
				val context = call.receive<PostData>()
				println(context)
				call.respondText("Customer stored correctly", status = HttpStatusCode.Created)
			} catch (ex: IllegalStateException) {
				call.respond(HttpStatusCode.BadRequest)
			} catch (ex: JsonConvertException) {
				call.respond(HttpStatusCode.BadRequest)
			}
		}

		//TODO : Get URL
    }
}
