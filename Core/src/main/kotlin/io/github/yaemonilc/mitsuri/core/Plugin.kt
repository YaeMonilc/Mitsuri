package io.github.yaemonilc.mitsuri.core

import io.github.yaemonilc.mitsuri.core.manager.PluginManager
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.reflect.KClass

abstract class Plugin {
    companion object {
        fun getInstance(
            klass: KClass<out Plugin>
        ): Plugin = PluginManager.getPluginInstance(
            klass = klass
        ) ?: throw IllegalStateException("Plugin instance not found")
    }

    abstract suspend fun onRegister()
    abstract suspend fun onCompleted()

    fun getPrivateDirectory() =
        PluginManager.getPluginPrivateDirectory(
            klass = this::class
        )!!.apply {
            if (!exists())
                mkdirs()
        }

    fun getLogger(): Logger = LoggerFactory.getLogger(this::class.java)
}