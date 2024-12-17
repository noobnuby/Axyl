package com.noobnuby.api.utils

import kotlin.math.pow

val words = ('0'..'9') + ('A'..'Z') + ('a'..'z')

fun Long.encodeToBase62(): String {
	var result = ""
	var index = this
	while(index > 0) {
		result = words[(index % 62).toInt()] + result
		index /= 62
	}
	return result
}

fun String.decodeBase62() =
	this.reversed().foldIndexed(0L) { index, acc, c ->
		acc + (words.binarySearch(c) * 62.toDouble().pow(index)).toLong()
	}