package io.github.yaemonilc.mitsuri.onebot.entity.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class Response(
    val status: Status,
    val retcode: Int,
    val data: JsonElement,
    val message: String,
    val stream: Stream,
    val echo: String,
    val wording: String
)

@Serializable
enum class Status {
    @SerialName("ok")
    OK
}

@Serializable
enum class Stream {
    @SerialName("normal-action")
    NormalAction,
}