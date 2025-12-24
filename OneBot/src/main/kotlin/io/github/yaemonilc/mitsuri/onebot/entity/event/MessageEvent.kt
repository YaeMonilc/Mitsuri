package io.github.yaemonilc.mitsuri.onebot.entity.event

import io.github.yaemonilc.mitsuri.onebot.entity.type.FriendSender
import io.github.yaemonilc.mitsuri.onebot.entity.type.GroupSender
import io.github.yaemonilc.mitsuri.onebot.entity.type.Segment
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class MessageEvent : Event() {
    override val postType: PostType = PostType.MESSAGE
    abstract val messageType: MessageType
    abstract val subType: SubType
    abstract val realSeq: String?
    abstract val messageId: Long
    abstract val messageSeq: Long
    abstract val realId: Long
    abstract val userId: Long
    abstract val message: List<Segment<*>>
    abstract val messageFormat: MessageFormat
    abstract val rawMessage: String
    abstract val font: Int
}

@Serializable
data class GroupMessageEvent(
    override val messageType: MessageType = MessageType.GROUP,
    override val subType: SubType = SubType.NORMAL,
    override val realSeq: String?,
    override val messageId: Long,
    override val messageSeq: Long,
    override val realId: Long,
    override val userId: Long,
    override val message: List<Segment<*>>,
    override val messageFormat: MessageFormat,
    override val rawMessage: String,
    override val font: Int,
    override val selfId: Long,
    override val time: Long,
    val sender: GroupSender,
    val groupId: Long,
    val groupName: String
) : MessageEvent()

@Serializable
data class FriendMessageEvent(
    override val messageType: MessageType = MessageType.PRIVATE,
    override val subType: SubType = SubType.FRIEND,
    override val realSeq: String?,
    override val messageId: Long,
    override val messageSeq: Long,
    override val realId: Long,
    override val userId: Long,
    override val message: List<Segment<*>>,
    override val messageFormat: MessageFormat,
    override val rawMessage: String,
    override val font: Int,
    override val selfId: Long,
    override val time: Long,
    val sender: FriendSender,
    val targetId: Long
) : MessageEvent()

@Serializable
sealed class GroupTempMessageEvent(
    override val messageType: MessageType = MessageType.PRIVATE,
    override val subType: SubType = SubType.GROUP,
    override val realSeq: String?,
    override val messageId: Long,
    override val messageSeq: Long,
    override val realId: Long,
    override val userId: Long,
    override val message: List<Segment<*>>,
    override val messageFormat: MessageFormat,
    override val rawMessage: String,
    override val font: Int,
    override val selfId: Long,
    override val time: Long,
    val sender: FriendSender,
    val groupId: Long,
    val targetId: Long
) : MessageEvent()

@Serializable
enum class MessageType {
    @SerialName("private")
    PRIVATE,
    @SerialName("group")
    GROUP
}

@Serializable
enum class SubType {
    @SerialName("friend")
    FRIEND,
    @SerialName("group")
    GROUP,
    @SerialName("normal")
    NORMAL

}

@Serializable
enum class MessageFormat {
    @SerialName("array")
    ARRAY,
    @SerialName("string")
    STRING,
}