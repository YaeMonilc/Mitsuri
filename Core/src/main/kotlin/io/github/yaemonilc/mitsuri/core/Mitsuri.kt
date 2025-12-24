package io.github.yaemonilc.mitsuri.core

import io.github.yaemonilc.mitsuri.core.manager.OneBotManager
import io.github.yaemonilc.mitsuri.core.manager.PluginManager
import io.github.yaemonilc.mitsuri.onebot.entity.response.Response
import io.github.yaemonilc.mitsuri.onebot.entity.event.Event
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterIsInstance
import java.io.File
import kotlin.reflect.KClass

object Mitsuri {
    private val receiveChannel
        get() = OneBotManager.receiveChannel
    private val actionChannel
        get() = OneBotManager.actionChannel

    suspend fun createBot(
        id: String,
        url: String
    ) = OneBotManager.createBot(
        id = id,
        url = url
    )

    fun registerPlugin(
        file: File,
        privateDirectory: File
    ) = PluginManager.registerPlugin(
        file = file,
        privateDirectory = privateDirectory
    )

    fun subscribe() = receiveChannel

    fun subscribeById(
        id: String
    ) = subscribe().filter { (session, _) -> session.id == id }

    fun subscribeEvent() = subscribe().filterIsInstance<Event>()

    fun subscribeEvent(
        klass: KClass<out Event>
    ) = subscribe().filterIsInstance(klass)

    fun subscribeEventById(
        id: String,
        klass: KClass<out Event>
    ) = subscribeById(
        id = id
    ).filterIsInstance(klass)

    fun subscribeResponse() = subscribe().filterIsInstance<Response>()

    fun <T> subscribeResponse(
        klass: KClass<out Response>
    ) = subscribe().filterIsInstance(klass)

    fun <T> subscribeResponseById(
        id: String,
        klass: KClass<out Response>,
    ) = subscribeById(
        id = id
    ).filterIsInstance(klass)

    fun subscribeAction() = actionChannel
}