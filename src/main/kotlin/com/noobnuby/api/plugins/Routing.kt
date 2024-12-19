package com.noobnuby.api.plugins

import com.noobnuby.api.data.PostData
import com.noobnuby.api.data.ResponseKeyData
import com.noobnuby.api.data.UrlTable
import com.noobnuby.api.utils.DataBase
import com.noobnuby.api.utils.decodeBase62
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
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.transactions.transaction

private const val ID_OFFSET = 10000000000

fun Application.configureRouting() {
	DataBase.register()
    routing {
		post("/shorten") {
			try {
				val context = call.receive<PostData>()
				println("https://www.jetbrains.com/".isUrl())
				if (!context.url.isUrl()) {
					call.respond(HttpStatusCode.BadRequest)
					return@post
				}

				val id = transaction {
					val existing = transaction {
						UrlTable.select(UrlTable.id)
							.where(UrlTable.url eq context.url)
					}
					if (!existing.empty()) {
						existing.single()[UrlTable.id]
					} else {
						UrlTable.insertAndGetId {
							it[url] = context.url
						}
					}
				}

				call.respond(Json.encodeToJsonElement(ResponseKeyData(id.value.plus(ID_OFFSET).encodeToBase62())))
			} catch (ex: IllegalStateException) {
				ex.printStackTrace()
				call.respond(HttpStatusCode.BadRequest)
			} catch (ex: JsonConvertException) {
				ex.printStackTrace()
				call.respond(HttpStatusCode.BadRequest)
			}
		}

		get("/{key}") {
			val key = call.parameters["key"] ?: return@get

			val url = transaction {
				val existing = transaction {
					UrlTable.select(UrlTable.url)
						.where(UrlTable.id eq key.decodeBase62().minus(ID_OFFSET))
				}
				if (!existing.empty()) {
					existing.single()[UrlTable.url]
				} else {
					"/"
				}
			}

			call.respondRedirect(url)
		}
    }
}