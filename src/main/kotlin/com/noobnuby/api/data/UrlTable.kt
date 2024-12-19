package com.noobnuby.api.data

import org.jetbrains.exposed.dao.id.LongIdTable

object UrlTable: LongIdTable() {
	val url = varchar("url", 2000).uniqueIndex()
}