package com.noobnuby.api.utils

val words = ('a'..'z') + ('A'..'Z') + ('0'..'9')

fun Long.encodeToBase62(): String {
	var result = ""
	var index = this
	while(index > 0) {
		result = words[(index % 62).toInt()] + result
		index /= 62
	}
	return result
}

//fun String.decodeToBase62() : Long {
//	var result = 0
//	var index = this
//	val
//}