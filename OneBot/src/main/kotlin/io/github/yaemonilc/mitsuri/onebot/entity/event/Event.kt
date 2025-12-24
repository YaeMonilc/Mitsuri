package io.github.yaemonilc.mitsuri.onebot.entity.event

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonEncoder
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

@Serializable(Event.Companion.Serializer::class)
sealed class Event {
    abstract val postType: PostType
    abstract val selfId: Long
    abstract val time: Long

    companion object {
        private object Serializer : KSerializer<Event> {
            override val descriptor: SerialDescriptor
                get() = buildClassSerialDescriptor(Event::class.qualifiedName!!)

            override fun serialize(
                encoder: Encoder,
                value: Event
            ) = (encoder as JsonEncoder).let { encoder ->
                encoder.json.encodeToJsonElement(
                    serializer = when (value) {
                        is HeartbeatEvent -> HeartbeatEvent.serializer()
                        is LifecycleEvent -> LifecycleEvent.serializer()
                        is MetaEvent -> MetaEvent.serializer()
                        is FriendRequestEvent -> FriendRequestEvent.serializer()
                        is GroupRequestEvent -> GroupRequestEvent.serializer()
                        is RequestEvent -> RequestEvent.serializer()
                        is BotOfflineEvent -> BotOfflineEvent.serializer()
                        is FriendAddNoticeEvent -> FriendAddNoticeEvent.serializer()
                        is FriendRecallNoticeEvent -> FriendRecallNoticeEvent.serializer()
                        is GroupAdminNoticeEvent -> GroupAdminNoticeEvent.serializer()
                        is GroupBanEvent -> GroupBanEvent.serializer()
                        is GroupCardEvent -> GroupCardEvent.serializer()
                        is GroupDecreaseEvent -> GroupDecreaseEvent.serializer()
                        is GroupEssenceEvent -> GroupEssenceEvent.serializer()
                        is GroupIncreaseEvent -> GroupIncreaseEvent.serializer()
                        is GroupNameEvent -> GroupNameEvent.serializer()
                        is GroupRecallNoticeEvent -> GroupRecallNoticeEvent.serializer()
                        is GroupTitleEvent -> GroupTitleEvent.serializer()
                        is GroupUploadNoticeEvent -> GroupUploadNoticeEvent.serializer()
                        is InputStatusEvent -> InputStatusEvent.serializer()
                        is GroupMsgEmojiLikeEvent -> GroupMsgEmojiLikeEvent.serializer()
                        is FriendPokeEvent -> FriendPokeEvent.serializer()
                        is GroupPokeEvent -> GroupPokeEvent.serializer()
                        is ProfileLikeEvent -> ProfileLikeEvent.serializer()
                        is PokeEvent -> PokeEvent.serializer()
                        is GroupNoticeEvent -> GroupNoticeEvent.serializer()
                        is GroupNotifyEvent -> GroupNotifyEvent.serializer()
                        is BaseNoticeEvent -> BaseNoticeEvent.serializer()
                        is GroupMessageEvent -> GroupMessageEvent.serializer()
                        is FriendMessageEvent -> FriendMessageEvent.serializer()
                        is GroupTempMessageEvent -> GroupTempMessageEvent.serializer()
                        is MessageEvent -> MessageEvent.serializer()
                    } as KSerializer<Any>,
                    value = value
                ).jsonObject.let {
                    encoder.encodeJsonElement(it)
                }
            }

            override fun deserialize(
                decoder: Decoder
            ): Event = (decoder as JsonDecoder).decodeJsonElement().jsonObject.let { obj ->
                decoder.json.decodeFromJsonElement(
                    deserializer =
                        when (obj["post_type"]?.jsonPrimitive?.content) {
                            "meta_event" -> {
                                when (obj["meta_event_type"]?.jsonPrimitive?.content) {
                                    "heartbeat" -> HeartbeatEvent.serializer()
                                    "lifecycle" -> LifecycleEvent.serializer()
                                    else -> throw SerializationException()
                                }
                            }
                            "request" -> {
                                when (obj["request_type"]?.jsonPrimitive?.content) {
                                    "friend" -> FriendRequestEvent.serializer()
                                    "group" -> GroupRequestEvent.serializer()
                                    else -> throw SerializationException()
                                }
                            }
                            "notice" -> {
                                when (obj["notice_type"]?.jsonPrimitive?.content) {
                                    "bot_offline" -> BotOfflineEvent.serializer()
                                    "friend_add" -> FriendAddNoticeEvent.serializer()
                                    "friend_recall" -> FriendRecallNoticeEvent.serializer()
                                    "group_admin" -> GroupAdminNoticeEvent.serializer()
                                    "group_ban" -> GroupBanEvent.serializer()
                                    "group_card" -> GroupCardEvent.serializer()
                                    "group_decrease" -> GroupDecreaseEvent.serializer()
                                    "essence" -> GroupEssenceEvent.serializer()
                                    "group_increase" -> GroupIncreaseEvent.serializer()
                                    "group_recall" -> GroupRecallNoticeEvent.serializer()
                                    "group_upload" -> GroupUploadNoticeEvent.serializer()
                                    "group_msg_emoji_like" -> GroupMsgEmojiLikeEvent.serializer()
                                    "notify" -> {
                                        when (obj["sub_type"]?.jsonPrimitive?.content) {
                                            "group_name" -> GroupNameEvent.serializer()
                                            "title" -> GroupTitleEvent.serializer()
                                            "input_status" -> InputStatusEvent.serializer()
                                            "poke" -> {
                                                if (obj["group_id"]?.jsonPrimitive?.contentOrNull != null) {
                                                    GroupPokeEvent.serializer()
                                                } else {
                                                    FriendPokeEvent.serializer()
                                                }
                                            }
                                            "profile_like" -> ProfileLikeEvent.serializer()
                                            else -> throw SerializationException()
                                        }
                                    }
                                    else -> throw SerializationException()
                                }
                            }
                            "message" -> {
                                obj["message_type"]?.jsonPrimitive?.content?.let { messageType ->
                                    obj["sub_type"]?.jsonPrimitive?.content?.let { subType ->
                                        when (messageType) {
                                            "private" if subType == "group" -> GroupTempMessageEvent.serializer()
                                            "private" -> FriendMessageEvent.serializer()
                                            "group" -> GroupMessageEvent.serializer()
                                            else -> MessageEvent.serializer()
                                        }
                                    } ?: throw SerializationException()
                                } ?: throw SerializationException()
                            }
                            else -> throw SerializationException()
                        },
                    element = obj
                )
            }

        }
    }
}

@Serializable
enum class PostType {
    @SerialName("meta_event")
    META,
    @SerialName("request")
    REQUEST,
    @SerialName("notice")
    NOTICE,
    @SerialName("message")
    MESSAGE;
}

