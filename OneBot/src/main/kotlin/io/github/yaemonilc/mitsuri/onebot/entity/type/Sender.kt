package io.github.yaemonilc.mitsuri.onebot.entity.type

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FriendSender(
    val userId: Long,
    val nickname: String,
    val card: String
)

@Serializable
data class GroupSender(
    val userId: Long,
    val nickname: String,
    val card: String,
    val role: Role
)

@Serializable
enum class Role {
    @SerialName("owner")
    OWNER,
    @SerialName("admin")
    ADMIN,
    @SerialName("member")
    MEMBER
}