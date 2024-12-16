package com.noobnuby.api.plugins

import com.noobnuby.api.data.PostData
import com.noobnuby.api.data.ResponseKeyData
import com.noobnuby.api.utils.encodeToBase62
import com.noobnuby.api.utils.isUrl
import io.ktor.http.*
import io.ktor.serialization.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import kotlin.random.Random

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

		//TODO : Add DataBase
		//TODO : Key Logic
		post("/url") {
			var id: Long = 0
			var success = false
			try {
				val context = call.receive<PostData>()
				println("https://www.jetbrains.com/".isUrl())
				if(context.url.isUrl()) {
					id = Random.nextLong(100000000000,1000000000000)
					success = true
				}
				call.respond(Json.encodeToJsonElement(ResponseKeyData(success, id.encodeToBase62())))
			} catch (ex: IllegalStateException) {
				call.respond(HttpStatusCode.BadRequest)
			} catch (ex: JsonConvertException) {
				call.respond(HttpStatusCode.BadRequest)
			}
		}

		//TODO : Get URL
    }
}
