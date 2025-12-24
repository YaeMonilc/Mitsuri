package io.github.yaemonilc.mitsuri.onebot.entity.event

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class RequestEvent : Event() {
    override val postType: PostType = PostType.REQUEST
    abstract val requestType: RequestType
}

@Serializable
enum class RequestType {
    @SerialName("friend")
    FRIEND,
    @SerialName("group")
    GROUP
}

@Serializable
data class FriendRequestEvent(
    override val selfId: Long,
    override val time: Long,
    override val requestType: RequestType = RequestType.FRIEND,
    val userId: Long,
    val comment: String,
    val flag: String
) : RequestEvent()

@Serializable
data class GroupRequestEvent(
    override val selfId: Long,
    override val time: Long,
    override val requestType: RequestType = RequestType.FRIEND,
    val groupId: Long,
    val userId: Long,
    val comment: String,
    val flag: String,
    val subType: String
) : RequestEvent()