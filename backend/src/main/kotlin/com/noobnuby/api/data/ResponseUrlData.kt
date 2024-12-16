package com.noobnuby.api.data

import kotlinx.serialization.Serializable

@Serializable
data class ResponseUrlData(val success: Boolean, val url:String)
