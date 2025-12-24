package io.github.yaemonilc.mitsuri.onebot.entity.event

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class MetaEvent : Event() {
    override val postType: PostType = PostType.META

    abstract val metaEventType: MetaEventType
}

@Serializable
enum class MetaEventType {
    @SerialName("heartbeat")
    HEARTBEAT,
    @SerialName("lifecycle")
    LIFECYCLE
}

@Serializable
data class HeartbeatEvent(
    override val selfId: Long,
    override val time: Long,
    override val metaEventType: MetaEventType = MetaEventType.HEARTBEAT,
    val status: Status,
    val interval: Long
) : MetaEvent() {
    @Serializable
    data class Status(
        val online: Boolean? = null,
        val good: Boolean
    )
}

@Serializable
data class LifecycleEvent(
    override val selfId: Long,
    override val time: Long,
    override val metaEventType: MetaEventType = MetaEventType.LIFECYCLE,
    val subType: SubType
) : MetaEvent() {
    @Serializable
    enum class SubType {
        @SerialName("enable")
        ENABLE,
        @SerialName("disable")
        DISABLE,
        @SerialName("connect")
        CONNECT
    }
}