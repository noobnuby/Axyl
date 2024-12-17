package com.noobnuby.api.utils

import com.noobnuby.api.data.UrlTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.io.path.exists

object DataBase {
	fun register() {
		if(!Paths.get("./data").exists()) Files.createDirectory(Paths.get("./data"))
		Database.connect("jdbc:sqlite:./data/data.db", "org.sqlite.JDBC")

		transaction {
			SchemaUtils.create(UrlTable)
		}
	}
}