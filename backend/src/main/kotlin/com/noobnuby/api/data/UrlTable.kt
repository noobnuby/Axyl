package com.noobnuby.api.data

import org.jetbrains.exposed.sql.Table

object UrlTable: Table() {
	val id = integer("id").autoIncrement()
	val url = varchar("url", 100)

	override val primaryKey = PrimaryKey(id)
}