package io.github.yaemonilc.mitsuri.launcher

import io.github.yaemonilc.mitsuri.core.Mitsuri
import io.github.yaemonilc.mitsuri.launcher.entity.Config
import io.github.yaemonilc.mitsuri.launcher.manager.ConfigManager
import io.github.yaemonilc.mitsuri.launcher.util.getLogger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.suspendCancellableCoroutine
import java.io.File

internal fun main(
    args: Array<String>
) {
    runBlocking {
        suspendCancellableCoroutine<Any> {
            printInformation()

            ConfigManager.apply {
                getLogger<Mitsuri>().info("加载配置文件...")
                init()
            }.config.let {
                Mitsuri.apply {
                    initPlugins(
                        config = it
                    )
                    createBots(
                        config = it
                    )

                    collectSessionEvent()
                }
            }
        }
    }
}

private fun printInformation() {
    getLogger<Mitsuri>().info(
        """
            
            --------------------------------------------
              __  __   _   _                          _ 
             |  \/  | (_) | |_   ___   _   _   _ __  (_)
             | |\/| | | | | __| / __| | | | | | '__| | |
             | |  | | | | | |_  \__ \ | |_| | | |    | |
             |_|  |_| |_|  \__| |___/  \__,_| |_|    |_|
             
             -------------------------------------------
        """.trimIndent()
    )
}

private fun Mitsuri.initPlugins(
    config: Config
) {
    getLogger<Mitsuri>().info("加载插件...")

    File(config.plugin.path).apply {
        if (!exists()) {
            mkdirs()
            return@apply
        }

        File(config.plugin.privateDirectory).let { privateDirectory ->
            listFiles {
                it.name.endsWith(".jar")
            }.forEach { file ->
                getLogger<Mitsuri>().info("注册插件: $file")

                runCatching {
                    registerPlugin(
                        file = file,
                        privateDirectory = privateDirectory
                    )
                }.onFailure {
                    getLogger<Mitsuri>().error("注册插件 $file 时出现错误", it)
                }
            }
        }
    }
}

private fun Mitsuri.createBots(
    config: Config
) {
    getLogger<Mitsuri>().info("链接Bot...")

    config.bots.forEach { bot ->
        getLogger<Mitsuri>().info("链接: ${bot.id}")

        CoroutineScope(Default).launch {
            runCatching {
                createBot(
                    id = bot.id,
                    url = bot.url
                )
            }.onFailure {
                getLogger<Mitsuri>().error("Bot ${bot.id} 出现错误", it)
            }
        }
    }
}

private fun Mitsuri.collectSessionEvent() {
    CoroutineScope(Default).apply {
        launch {
            subscribe().collect { (session, event) ->
                getLogger<Mitsuri>().info("${session.id} <- $event")
            }
        }

        launch {
            subscribeAction().collect { (session, action) ->
                getLogger<Mitsuri>().info("${session.id} -> $action")
            }
        }
    }
}