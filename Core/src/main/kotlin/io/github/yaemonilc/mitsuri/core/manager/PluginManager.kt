package io.github.yaemonilc.mitsuri.core.manager

import io.github.yaemonilc.mitsuri.core.Plugin
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.io.File
import java.net.URLClassLoader
import java.util.jar.JarFile
import kotlin.reflect.KClass

internal object PluginManager {
    private val plugins = mutableMapOf<Plugin, File>()

    internal fun registerPlugin(
        file: File,
        privateDirectory: File
    ) {
        URLClassLoader(
            arrayOf(file.toURI().toURL()),
            Thread.currentThread().contextClassLoader
        ).let { classLoader ->
            JarFile(file).use { jarFile ->
                jarFile.entries()
                    .iterator()
                    .asSequence()
                    .toList().let { entities ->
                        entities.find {
                            it.name == "plugin-config"
                        }?.let { configEntity ->
                            classLoader.getResourceAsStream(configEntity.name)
                                .bufferedReader()
                                .readText().let { pluginClass ->
                                    entities.mapNotNull { entity ->
                                        entity.name.let { name ->
                                            if (name.endsWith(".class"))
                                                name.replace("/", ".")
                                                    .removeSuffix(".class")
                                            else null
                                        }
                                    }.toMutableList().apply {
                                        find {
                                            it == pluginClass
                                        }?.also {
                                            plugins[(classLoader.loadClass(it)
                                                .getDeclaredConstructor()
                                                .newInstance() as Plugin)
                                                .apply {
                                                    CoroutineScope(Default + SupervisorJob()).launch {
                                                        onRegister()
                                                        onCompleted()
                                                    }
                                                }] = privateDirectory.apply {
                                                    if (!exists())
                                                        mkdir()
                                                }.let { parentFile ->
                                                    this::class.let { klass ->
                                                        File(
                                                            File(parentFile, klass.qualifiedName!!),
                                                            klass.simpleName!!
                                                        ).also { directory ->
                                                            directory.mkdirs()
                                                        }
                                                    }
                                                }
                                        } ?: throw IllegalStateException("Plugin $pluginClass not found")

                                        remove(pluginClass)

                                        forEach { className ->
                                            classLoader.loadClass(className)
                                        }
                                    }
                                }
                        } ?: throw IllegalArgumentException("${file.name} plugin-config.properties file not found")
                    }
            }
        }
    }

    internal fun getPluginInstance(
        klass: KClass<out Plugin>
    ) = plugins.entries.find {
        klass.isInstance(it)
    }?.key

    internal fun getPluginPrivateDirectory(
        klass: KClass<out Plugin>
    ) = plugins.entries.find {
        klass.isInstance(it)
    }?.value
}