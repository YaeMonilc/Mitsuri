package io.github.yaemonilc.mitsuri.onebot.entity.event

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class BaseNoticeEvent : Event() {
    override val postType: PostType = PostType.NOTICE
    abstract val noticeType: NoticeType
}

@Serializable
enum class NoticeType {
    @SerialName("bot_offline")
    BOT_OFFLINE,
    @SerialName("friend_add")
    FRIEND_ADD,
    @SerialName("friend_recall")
    FRIEND_RECALL,
    @SerialName("group_admin")
    GROUP_ADMIN,
    @SerialName("group_ban")
    GROUP_BAN,
    @SerialName("group_card")
    GROUP_CARD,
    @SerialName("group_decrease")
    GROUP_DECREASE,
    @SerialName("essence")
    ESSENCE,
    @SerialName("group_increase")
    GROUP_INCREASE,
    @SerialName("notify")
    NOTIFY,
    @SerialName("group_recall")
    GROUP_RECALL,
    @SerialName("group_upload")
    GROUP_UPLOAD,
    @SerialName("group_msg_emoji_like")
    GROUP_MSG_EMOJI_LIKE
}

@Serializable
sealed class GroupNoticeEvent : BaseNoticeEvent() {
    abstract val groupId: Long
    abstract val userId: Long
}

@Serializable
sealed class GroupNotifyEvent : BaseNoticeEvent() {
    override val noticeType: NoticeType = NoticeType.NOTIFY
    abstract val subType: SubType

    @Serializable
    enum class SubType {
        @SerialName("group_name")
        GROUP_NAME,
        @SerialName("title")
        TITLE,
        @SerialName("input_status")
        INPUT_STATUS,
        @SerialName("poke")
        POKE,
        @SerialName("profile_like")
        PROFILE_LIKE
    }
}

@Serializable
data class BotOfflineEvent(
    override val selfId: Long,
    override val time: Long,
    override val noticeType: NoticeType = NoticeType.BOT_OFFLINE,
    val userId: Long,
    val tag: String,
    val message: String
) : BaseNoticeEvent()

@Serializable
data class FriendAddNoticeEvent(
    override val selfId: Long,
    override val time: Long,
    override val noticeType: NoticeType = NoticeType.FRIEND_ADD,
    val userId: Long
) : BaseNoticeEvent()

@Serializable
data class FriendRecallNoticeEvent(
    override val selfId: Long,
    override val time: Long,
    override val noticeType: NoticeType = NoticeType.FRIEND_RECALL,
    val userId: Long,
    val messageId: Long
) : BaseNoticeEvent()

@Serializable
data class GroupAdminNoticeEvent(
    override val selfId: Long,
    override val time: Long,
    override val noticeType: NoticeType = NoticeType.GROUP_ADMIN,
    override val groupId: Long,
    override val userId: Long,
    val subType: SubType
) : GroupNoticeEvent() {
    @Serializable
    enum class SubType {
        @SerialName("set")
        SET,
        @SerialName("unset")
        UNSET
    }
}

@Serializable
data class GroupBanEvent(
    override val selfId: Long,
    override val time: Long,
    override val noticeType: NoticeType = NoticeType.GROUP_BAN,
    override val groupId: Long,
    override val userId: Long,
    val operatorId: Long,
    val duration: Long,
    val subType: SubType
) : GroupNoticeEvent() {
    @Serializable
    enum class SubType {
        @SerialName("ban")
        BAN,
        @SerialName("lift_ban")
        LIFT_BAN
    }
}

@Serializable
data class GroupCardEvent(
    override val selfId: Long,
    override val time: Long,
    override val noticeType: NoticeType = NoticeType.GROUP_CARD,
    override val groupId: Long,
    override val userId: Long,
    val cardNew: String,
    val cardOdl: String
) : GroupNoticeEvent()

@Serializable
data class GroupDecreaseEvent(
    override val selfId: Long,
    override val time: Long,
    override val noticeType: NoticeType = NoticeType.GROUP_DECREASE,
    override val groupId: Long,
    override val userId: Long,
    val subType: SubType,
    val operatorId: Long,
) : GroupNoticeEvent() {
    @Serializable
    enum class SubType {
        @SerialName("leave")
        LEAVE,
        @SerialName("kick")
        KICK,
        @SerialName("kick_me")
        KICK_ME,
        @SerialName("disband")
        DISBAND
    }
}

@Serializable
data class GroupEssenceEvent(
    override val selfId: Long,
    override val time: Long,
    override val noticeType: NoticeType = NoticeType.ESSENCE,
    override val groupId: Long,
    override val userId: Long,
    val messageId: String,
    val senderId: Long,
    val operatorId: Long,
    val subType: SubType
) : GroupNoticeEvent() {
    @Serializable
    enum class SubType {
        @SerialName("add")
        ADD,
        @SerialName("delete")
        DELETE
    }
}

@Serializable
data class GroupIncreaseEvent(
    override val selfId: Long,
    override val time: Long,
    override val noticeType: NoticeType = NoticeType.GROUP_INCREASE,
    override val groupId: Long,
    override val userId: Long,
    val operatorId: Long,
    val subType: SubType
) : GroupNoticeEvent() {
    @Serializable
    enum class SubType {
        @SerialName("approve")
        APPROVE,
        @SerialName("invite")
        INVITE
    }
}

@Serializable
data class GroupNameEvent(
    override val selfId: Long,
    override val time: Long,
    override val subType: SubType = SubType.GROUP_NAME,
    val nameNew: String
) : GroupNotifyEvent()

@Serializable
data class GroupRecallNoticeEvent(
    override val selfId: Long,
    override val time: Long,
    override val noticeType: NoticeType = NoticeType.GROUP_RECALL,
    override val groupId: Long,
    override val userId: Long,
    val operatorId: Long,
    val messageId: Long
) : GroupNoticeEvent()

@Serializable
data class GroupTitleEvent(
    override val selfId: Long,
    override val time: Long,
    override val subType: SubType = SubType.TITLE,
    val title: String
) : GroupNotifyEvent()

@Serializable
data class GroupUploadNoticeEvent(
    override val selfId: Long,
    override val time: Long,
    override val noticeType: NoticeType = NoticeType.GROUP_UPLOAD,
    override val groupId: Long,
    override val userId: Long,
    val file: File
) : GroupNoticeEvent() {
    @Serializable
    data class File(
        val id: String,
        val name: String,
        val size: Long,
        val busid: Long
    )
}

@Serializable
data class InputStatusEvent(
    override val selfId: Long,
    override val time: Long,
    override val subType: SubType = SubType.INPUT_STATUS,
    val statusText: String,
    val eventType: Long,
    val userId: Long,
    val groupId: Long
) : GroupNotifyEvent()

@Serializable
data class GroupMsgEmojiLikeEvent(
    override val selfId: Long,
    override val time: Long,
    override val noticeType: NoticeType = NoticeType.GROUP_MSG_EMOJI_LIKE,
    override val groupId: Long,
    override val userId: Long,
    val messageId: Long,
    val likes: List<EmojiLike>,
    val isAdd: Boolean,
    val messageSeq: String?
) : GroupNoticeEvent() {
    @Serializable
    data class EmojiLike(
        val emojiId: String,
        val count: Long
    )
}

@Serializable
sealed class PokeEvent : GroupNotifyEvent() {
    override val subType: SubType = SubType.POKE
    abstract val targetId: Long
    abstract val userId: Long
    abstract val rawInfo: Nothing?
}

@Serializable
data class FriendPokeEvent(
    override val selfId: Long,
    override val time: Long,
    override val targetId: Long,
    override val userId: Long,
    override val rawInfo: Nothing?,
    val senderId: Long
) : PokeEvent()

@Serializable
data class GroupPokeEvent(
    override val selfId: Long,
    override val time: Long,
    override val targetId: Long,
    override val userId: Long,
    override val rawInfo: Nothing?,
    val groupId: Long
) : PokeEvent()

@Serializable
data class ProfileLikeEvent(
    override val selfId: Long,
    override val time: Long,
    override val subType: SubType = SubType.PROFILE_LIKE,
    val operatorId: Long,
    val operatorNick: String,
    val times: Long
) : GroupNotifyEvent()