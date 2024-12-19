package com.noobnuby.api.utils

import java.net.URI

fun String.isUrl(): Boolean {
	try { //using toURL Method throws MalformedURLException and IllegalArgumentException
		URI(this).toURL()
		return true
	} catch (e : Exception) {
		return false
	}
}