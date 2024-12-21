package com.noobnuby.api.plugins

import io.ktor.server.application.*
import io.ktor.server.pebble.*
import io.pebbletemplates.pebble.loader.ClasspathLoader

fun Application.configurePebble() {
	install(Pebble) {
		loader(ClasspathLoader().apply {
			prefix = "templates"
		})
	}
}