@file:OptIn(ExperimentalSerializationApi::class)

package io.github.yaemonilc.mitsuri.onebot.entity.action

import io.github.yaemonilc.mitsuri.onebot.entity.event.MessageType
import io.github.yaemonilc.mitsuri.onebot.entity.type.ForwardMessage
import io.github.yaemonilc.mitsuri.onebot.entity.type.Segment
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator

@Serializable
@JsonClassDiscriminator("action")
sealed class Action {
    abstract val echo: String?
}

@Serializable
@SerialName("get_login_info")
data class GetLoginInfo(
    override val echo: String? = null
) : Action()

@Serializable
@SerialName("get_status")
data class GetStatus(
    override val echo: String? = null
) : Action()

@Serializable
@SerialName("get_version_info")
data class GetVersionInfo(
    override val echo: String? = null
) : Action()

@Serializable
@SerialName("bot_exit")
data class BotExit(
    override val echo: String? = null
) : Action()

@Serializable
@SerialName("clean_cache")
data class CleanCache(
    override val echo: String? = null
) : Action()

@Serializable
@SerialName("set_self_longnick")
data class SetSelfLongnick(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val longNick: String
    )
}

@Serializable
@SerialName("set_input_status")
data class SetInputStatus(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val userId: Long,
        val eventType: Int
    )
}

@Serializable
@SerialName("set_diy_online_status")
data class SetDiyOnlineStatus(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val faceId: Long,
        val faceType: String,
        val wording: String
    )
}

@Serializable
@SerialName("set_online_status")
data class SetOnlineStatus(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val status: Int
    )
}

@Serializable
@SerialName("set_qq_profile")
data class SetQqProfile(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val nickname: String? = null,
        val company: String? = null,
        val email: String? = null,
        val college: String? = null,
        val personalNote: String? = null
    )
}

@Serializable
@SerialName("set_qq_avatar")
data class SetQqAvatar(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val file: String
    )
}

@Serializable
@SerialName("get_clientkey")
data class GetClientkey(
    override val echo: String? = null
) : Action()

@Serializable
@SerialName("get_friend_list")
data class GetFriendList(
    override val echo: String? = null,
    val params: Params? = null
) : Action() {
    @Serializable
    data class Params(
        val noCache: Boolean? = null
    )
}

@Serializable
@SerialName("get_friends_with_category")
data class GetFriendsWithCategory(
    override val echo: String? = null
) : Action()

@Serializable
@SerialName("send_private_msg")
data class SendPrivateMsg(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val userId: Long,
        val message: List<Segment<*>>,
        val autoEscape: Boolean? = null
    )
}

@Serializable
@SerialName("delete_msg")
data class DeleteMsg(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val messageId: Long
    )
}

@Serializable
@SerialName("get_msg")
data class GetMsg(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val messageId: Long
    )
}

@Serializable
@SerialName("send_like")
data class SendLike(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val userId: Long,
        val times: Int
    )
}

@Serializable
@SerialName("set_friend_add_request")
data class SetFriendAddRequest(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val flag: String,
        val approve: Boolean,
        val remark: String? = null
    )
}

@Serializable
@SerialName("set_friend_remark")
data class SetFriendRemark(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val userId: Long,
        val remark: String
    )
}

@Serializable
@SerialName("delete_friend")
data class DeleteFriend(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val userId: Long
    )
}

@Serializable
@SerialName("get_unidirectional_friend_list")
data class GetUnidirectionalFriendList(
    override val echo: String? = null
) : Action()

@Serializable
@SerialName("friend_poke")
data class FriendPoke(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val userId: Long
    )
}

@Serializable
@SerialName("mark_private_msg_as_read")
data class MarkPrivateMsgAsRead(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val userId: Long,
        val time: Int
    )
}

@Serializable
@SerialName("get_friend_msg_history")
data class GetFriendMsgHistory(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val userId: Long,
        val count: Int
    )
}

@Serializable
@SerialName("forward_friend_single_msg")
data class ForwardFriendSingleMsg(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val userId: Long,
        val messageId: Long
    )
}

@Serializable
@SerialName("get_profile_like")
data class GetProfileLike(
    override val echo: String? = null
) : Action()

@Serializable
@SerialName("fetch_emoji_like")
data class FetchEmojiLike(
    override val echo: String? = null
) : Action()

@Serializable
@SerialName("nc_get_user_status")
data class NcGetUserStatus(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val userId: Long
    )
}

@Serializable
@SerialName("get_group_list")
data class GetGroupList(
    override val echo: String? = null,
    val params: Params? = null
) : Action() {
    @Serializable
    data class Params(
        val noCache: Boolean? = null
    )
}

@Serializable
@SerialName("get_group_info")
data class GetGroupInfo(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val groupId: Long,
        val noCache: Boolean? = null
    )
}

@Serializable
@SerialName("get_group_info_ex")
data class GetGroupInfoEx(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val groupId: Long
    )
}

@Serializable
@SerialName("send_group_msg")
data class SendGroupMsg(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val groupId: Long,
        val message: List<Segment<*>>,
        val autoEscape: Boolean? = null
    )
}

@Serializable
@SerialName("set_group_add_request")
data class SetGroupAddRequest(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val flag: String,
        val approve: Boolean,
        val reason: String? = null
    )
}

@Serializable
@SerialName("set_group_kick")
data class SetGroupKick(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val groupId: Long,
        val userId: Long,
        val rejectAddRequest: Boolean? = null
    )
}

@Serializable
@SerialName("set_group_ban")
data class SetGroupBan(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val groupId: Long,
        val userId: Long,
        val duration: Long
    )
}

@Serializable
@SerialName("set_group_whole_ban")
data class SetGroupWholeBan(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val groupId: Long,
        val enable: Boolean
    )
}

@Serializable
@SerialName("set_group_admin")
data class SetGroupAdmin(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val groupId: Long,
        val userId: Long,
        val enable: Boolean
    )
}

@Serializable
@SerialName("set_group_card")
data class SetGroupCard(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val groupId: Long,
        val userId: Long,
        val card: String
    )
}

@Serializable
@SerialName("set_group_name")
data class SetGroupName(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val groupId: Long,
        val groupName: String
    )
}

@Serializable
@SerialName("set_group_leave")
data class SetGroupLeave(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val groupId: Long,
        val isDismiss: Boolean? = null
    )
}

@Serializable
@SerialName("set_group_special_title")
data class SetGroupSpecialTitle(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val groupId: Long,
        val userId: Long,
        val specialTitle: String
    )
}

@Serializable
@SerialName("get_group_member_info")
data class GetGroupMemberInfo(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val groupId: Long,
        val userId: Long,
        val noCache: Boolean? = null
    )
}

@Serializable
@SerialName("get_group_member_list")
data class GetGroupMemberList(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val groupId: Long,
        val noCache: Boolean? = null
    )
}

@Serializable
@SerialName("get_group_honor_info")
data class GetGroupHonorInfo(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val groupId: Long,
        val type: String? = null
    )
}

@Serializable
@SerialName("get_essence_msg_list")
data class GetEssenceMsgList(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val groupId: Long
    )
}

@Serializable
@SerialName("set_essence_msg")
data class SetEssenceMsg(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val messageId: String
    )
}

@Serializable
@SerialName("delete_essence_msg")
data class DeleteEssenceMsg(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val messageId: String
    )
}

@Serializable
@SerialName("group_poke")
data class GroupPoke(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val groupId: Long,
        val userId: Long
    )
}

@Serializable
@SerialName("mark_group_msg_as_read")
data class MarkGroupMsgAsRead(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val groupId: Long,
        val time: Int
    )
}

@Serializable
@SerialName("forward_group_single_msg")
data class ForwardGroupSingleMsg(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val groupId: Long,
        val messageId: String
    )
}

@Serializable
@SerialName("set_group_portrait")
data class SetGroupPortrait(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val groupId: Long,
        val file: String,
        val cache: Int? = null
    )
}

@Serializable
@SerialName("_send_group_notice")
data class SendGroupNotice(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val groupId: Long,
        val content: String,
        val image: String? = null,
        val pinned: Boolean? = null,
        val confirmRequired: Boolean? = null
    )
}

@Serializable
@SerialName("_get_group_notice")
data class GetGroupNotice(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val groupId: Long
    )
}

@Serializable
@SerialName("_del_group_notice")
data class DelGroupNotice(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val groupId: Long,
        val noticeId: String
    )
}

@Serializable
@SerialName("get_group_at_all_remain")
data class GetGroupAtAllRemain(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val groupId: Long
    )
}

@Serializable
@SerialName("get_group_ignore_add_request")
data class GetGroupIgnoreAddRequest(
    override val echo: String? = null
) : Action()

@Serializable
@SerialName("get_group_ignored_notifies")
data class GetGroupIgnoredNotifies(
    override val echo: String? = null
) : Action()

@Serializable
@SerialName("get_group_system_msg")
data class GetGroupSystemMsg(
    override val echo: String? = null
) : Action()

@Serializable
@SerialName("get_group_shut_list")
data class GetGroupShutList(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val groupId: Long
    )
}

@Serializable
@SerialName("set_group_remark")
data class SetGroupRemark(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val groupId: Long,
        val remark: String
    )
}

@Serializable
@SerialName("set_group_sign")
data class SetGroupSign(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val groupId: Long
    )
}

@Serializable
@SerialName("send_group_sign")
data class SendGroupSign(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val groupId: Long
    )
}

@Serializable
@SerialName("send_msg")
data class SendMsg(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val messageType: MessageType,
        val userId: Long? = null,
        val groupId: Long? = null,
        val message: List<Segment<*>>,
        val autoEscape: Boolean? = null
    )
}

@Serializable
@SerialName("get_record")
data class GetRecord(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val file: String,
        val outFormat: String? = null
    )
}

@Serializable
@SerialName("get_image")
data class GetImage(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val file: String
    )
}

@Serializable
@SerialName("can_send_image")
data class CanSendImage(
    override val echo: String? = null
) : Action()

@Serializable
@SerialName("can_send_record")
data class CanSendRecord(
    override val echo: String? = null
) : Action()

@Serializable
@SerialName("get_file")
data class GetFile(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val file: String,
        val type: String
    )
}

@Serializable
@SerialName("_mark_all_as_read")
data class MarkAllAsRead(
    override val echo: String? = null
) : Action()

@Serializable
@SerialName("ocr_image")
data class OcrImage(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val image: String
    )
}

@Serializable
@SerialName(".ocr_image")
data class DotOcrImage(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val image: String
    )
}

@Serializable
@SerialName("get_recent_contact")
data class GetRecentContact(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val count: String
    )
}

@Serializable
@SerialName("send_poke")
data class SendPoke(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val userId: Long? = null,
        val groupId: Long? = null,
        val targetId: Long
    )
}

@Serializable
@SerialName("get_forward_msg")
data class GetForwardMsg(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val messageId: Long
    )
}

@Serializable
@SerialName("mark_msg_as_read")
data class MarkMsgAsRead(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val messageId: Long
    )
}

@Serializable
@SerialName("upload_group_file")
data class UploadGroupFile(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val groupId: Long,
        val file: String,
        val name: String,
        val folder: String? = null
    )
}

@Serializable
@SerialName("delete_group_file")
data class DeleteGroupFile(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val groupId: Long,
        val fileId: String,
        val busid: Int
    )
}

@Serializable
@SerialName("create_group_file_folder")
data class CreateGroupFileFolder(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val groupId: Long,
        val name: String
    )
}

@Serializable
@SerialName("delete_group_folder")
data class DeleteGroupFolder(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val groupId: Long,
        val folderId: String
    )
}

@Serializable
@SerialName("get_group_file_system_info")
data class GetGroupFileSystemInfo(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val groupId: Long
    )
}

@Serializable
@SerialName("get_group_root_files")
data class GetGroupRootFiles(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val groupId: Long
    )
}

@Serializable
@SerialName("get_group_files_by_folder")
data class GetGroupFilesByFolder(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val groupId: Long,
        val folderId: String
    )
}

@Serializable
@SerialName("get_group_file_url")
data class GetGroupFileUrl(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val groupId: Long,
        val fileId: String,
        val busid: Int
    )
}

@Serializable
@SerialName("move_group_file")
data class MoveGroupFile(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val groupId: Long,
        val fileId: String,
        val targetDir: String
    )
}

@Serializable
@SerialName("trans_group_file")
data class TransGroupFile(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val groupId: Long,
        val fileId: String,
        val targetGroupId: Long
    )
}

@Serializable
@SerialName("rename_group_file")
data class RenameGroupFile(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val groupId: Long,
        val fileId: String,
        val currentParentDirectory: String,
        val newName: String
    )
}

@Serializable
@SerialName("upload_private_file")
data class UploadPrivateFile(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val userId: Long,
        val file: String,
        val name: String
    )
}

@Serializable
@SerialName("get_private_file_url")
data class GetPrivateFileUrl(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val fileId: String
    )
}

@Serializable
@SerialName("download_file")
data class DownloadFile(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val url: String,
        val threadCount: Int,
        val headers: String
    )
}

@Serializable
@SerialName("get_ai_characters")
data class GetAiCharacters(
    override val echo: String? = null
) : Action()

@Serializable
@SerialName("get_ai_record")
data class GetAiRecord(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val character: String,
        val groupId: Long,
        val text: String
    )
}

@Serializable
@SerialName("send_group_ai_record")
data class SendGroupAiRecord(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val character: String,
        val groupId: Long,
        val text: String
    )
}

@Serializable
@SerialName("send_group_forward_msg")
data class SendGroupForwardMsg(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val groupId: Long,
        val messages: List<ForwardMessage>
    )
}

@Serializable
@SerialName("send_private_forward_msg")
data class SendPrivateForwardMsg(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val userId: Long,
        val messages: List<ForwardMessage>
    )
}

@Serializable
@SerialName("send_forward_msg")
data class SendForwardMsg(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val messages: List<ForwardMessage>
    )
}

@Serializable
@SerialName("ArkSharePeer")
data class ArkSharePeer(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val userId: Long
    )
}

@Serializable
@SerialName("ArkShareGroup")
data class ArkShareGroup(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val groupId: Long
    )
}

@Serializable
@SerialName("get_mini_app_ark")
data class GetMiniAppArk(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val appid: String,
        val view: String,
        val payload: String
    )
}

@Serializable
@SerialName("get_cookies")
data class GetCookies(
    override val echo: String? = null,
    val params: Params? = null
) : Action() {
    @Serializable
    data class Params(
        val domain: String? = null
    )
}

@Serializable
@SerialName("get_csrf_token")
data class GetCsrfToken(
    override val echo: String? = null
) : Action()

@Serializable
@SerialName("get_credentials")
data class GetCredentials(
    override val echo: String? = null,
    val params: Params? = null
) : Action() {
    @Serializable
    data class Params(
        val domain: String? = null
    )
}

@Serializable
@SerialName("get_doubt_friends_add_request")
data class GetDoubtFriendsAddRequest(
    override val echo: String? = null
) : Action()

@Serializable
@SerialName("set_doubt_friends_add_request")
data class SetDoubtFriendsAddRequest(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val flag: String,
        val approve: Boolean
    )
}

@Serializable
@SerialName("get_stranger_info")
data class GetStrangerInfo(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val userId: Long,
        val noCache: Boolean? = null
    )
}

@Serializable
@SerialName("get_rkey")
data class GetRkey(
    override val echo: String? = null
) : Action()

@Serializable
@SerialName("click_inline_keyboard_button")
data class ClickInlineKeyboardButton(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val groupId: Long,
        val botAppid: String,
        val buttonId: String,
        val callbackData: String,
        val msgSeq: String
    )
}

@Serializable
@SerialName("translate_en2zh")
data class TranslateEn2Zh(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val text: String
    )
}

@Serializable
@SerialName("create_collection")
data class CreateCollection(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val content: String
    )
}

@Serializable
@SerialName("get_collection_list")
data class GetCollectionList(
    override val echo: String? = null
) : Action()

@Serializable
@SerialName("fetch_custom_face")
data class FetchCustomFace(
    override val echo: String? = null
) : Action()

@Serializable
@SerialName("nc_get_packet_status")
data class NcGetPacketStatus(
    override val echo: String? = null
) : Action()

@Serializable
@SerialName("get_robot_uin_range")
data class GetRobotUinRange(
    override val echo: String? = null
) : Action()

@Serializable
@SerialName("get_guild_list")
data class GetGuildList(
    override val echo: String? = null
) : Action()

@Serializable
@SerialName("get_guild_service_profile")
data class GetGuildServiceProfile(
    override val echo: String? = null
) : Action()

@Serializable
@SerialName("_get_model_show")
data class GetModelShow(
    override val echo: String? = null
) : Action()

@Serializable
@SerialName("_set_model_show")
data class SetModelShow(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val model: String,
        val modelShow: String
    )
}

@Serializable
@SerialName("get_group_msg_history")
data class GetGroupMsgHistory(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val groupId: Long,
        val count: Int
    )
}

@Serializable
@SerialName("check_url_safely")
data class CheckUrlSafely(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val url: String
    )
}

@Serializable
@SerialName(".get_word_slices")
data class GetWordSlices(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val content: String
    )
}

@Serializable
@SerialName("handle_quick_operation")
data class HandleQuickOperation(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val context: Map<String, String>,
        val operation: Map<String, String>
    )
}

@Serializable
@SerialName("send_packet")
data class SendPacket(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val packet: String
    )
}

@Serializable
@SerialName("set_msg_emoji_like")
data class SetMsgEmojiLike(
    override val echo: String? = null,
    val params: Params
) : Action() {
    @Serializable
    data class Params(
        val messageId: Long,
        val emojiId: Long,
        val emojiType: Int? = null
    )
}

@Serializable
@SerialName("get_online_clients")
data class GetOnlineClients(
    override val echo: String? = null,
    val params: Params? = null
) : Action() {
    @Serializable
    data class Params(
        val noCache: Boolean? = null
    )
}
