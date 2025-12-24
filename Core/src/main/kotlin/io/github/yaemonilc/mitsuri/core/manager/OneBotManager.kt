@file:OptIn(ExperimentalCoroutinesApi::class)

package io.github.yaemonilc.mitsuri.core.manager

import io.github.yaemonilc.mitsuri.onebot.OneBot
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge

internal object OneBotManager {
    private val oneBot = OneBot()

    internal val receiveChannel
        get() = oneBot.sessions.flatMapLatest { sessions ->
            sessions.map { session ->
                session.receiveChannel.map {
                    Pair(
                        first = session,
                        second = it
                    )
                }
            }.merge()
        }

    internal val actionChannel
        get() = oneBot.sessions.flatMapLatest { sessions ->
            sessions.map { session ->
                session.actionChannel.map {
                    Pair(
                        first = session,
                        second = it
                    )
                }
            }.merge()
        }

    internal suspend fun createBot(
        id: String,
        url: String
    ) = oneBot.create(
        id = id,
        url = url
    )
}