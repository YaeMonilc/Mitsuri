@file:OptIn(ExperimentalSerializationApi::class)

package io.github.yaemonilc.mitsuri.onebot

import io.github.yaemonilc.mitsuri.onebot.entity.response.Response
import io.github.yaemonilc.mitsuri.onebot.entity.action.Action
import io.github.yaemonilc.mitsuri.onebot.entity.event.Event
import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.plugins.websocket.sendSerialized
import io.ktor.client.plugins.websocket.ws
import io.ktor.serialization.kotlinx.KotlinxWebsocketSerializationConverter
import io.ktor.websocket.Frame
import io.ktor.websocket.readText
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNamingStrategy
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement

class Session internal constructor(
    val id: String,
    private val url: String
) {
    companion object {
        private val json = Json {
            ignoreUnknownKeys = true
            encodeDefaults = true
            namingStrategy = JsonNamingStrategy.SnakeCase
        }
    }

    private val _receiveChannel = MutableSharedFlow<Any>()
    val receiveChannel: SharedFlow<Any>
        get() = _receiveChannel

    private val _actionChannel = MutableSharedFlow<Action>()
    val actionChannel: SharedFlow<Action>
        get() = _actionChannel

    private val client = HttpClient {
        install(WebSockets) {
            contentConverter = KotlinxWebsocketSerializationConverter(
                format = json
            )
        }
    }

    internal suspend fun connect() {
        client.ws(
            urlString = url
        ) {
            runCatching {
                launch {
                    _actionChannel.collect(::sendSerialized)
                }

                incoming.receiveAsFlow().collect {
                    if (it is Frame.Text) {
                        (json.parseToJsonElement(it.readText()) as JsonObject).let { obj ->
                            _receiveChannel.emit(
                                value = when {
                                    "status" in obj && "echo" in obj -> {
                                        json.decodeFromJsonElement<Response>(
                                            json = obj
                                        )
                                    }
                                    else -> {
                                        json.decodeFromJsonElement<Event>(
                                            json = obj
                                        )
                                    }
                                }
                            )
                        }
                    }
                }
            }.onFailure {
                it.printStackTrace()
            }
        }
    }

    suspend fun emitAction(
        action: Action
    ) = _actionChannel.emit(action)
}