package io.github.yaemonilc.mitsuri.onebot

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class OneBot {
    private val _sessions = MutableStateFlow(
        value = listOf<Session>()
    )
    val sessions: StateFlow<List<Session>>
        get() = _sessions

    suspend fun create(
        id: String,
        url: String
    ) = Session(
        id = id,
        url = url
    ).let { session ->
        _sessions.update {
            it + session
        }

        session.connect()
    }
}