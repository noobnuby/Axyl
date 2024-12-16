package com.noobnuby.api.data

import kotlinx.serialization.Serializable

@Serializable
data class ResponseKeyData(val success: Boolean, val key: String)
