package io.github.yaemonilc.mitsuri.onebot.entity.type

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class SegmentType {
    @SerialName("text")
    TEXT,
    @SerialName("image")
    IMAGE,
    @SerialName("music")
    MUSIC,
    @SerialName("video")
    VIDEO,
    @SerialName("record")
    VOICE,
    @SerialName("file")
    FILE,
    @SerialName("at")
    AT,
    @SerialName("reply")
    REPLY,
    @SerialName("json")
    JSON,
    @SerialName("face")
    FACE,
    @SerialName("mface")
    MFACE,
    @SerialName("markdown")
    MARKDOWN,
    @SerialName("node")
    NODE,
    @SerialName("forward")
    FORWARD,
    @SerialName("xml")
    XML,
    @SerialName("poke")
    POKE,
    @SerialName("dice")
    DICE,
    @SerialName("rps")
    RPS,
    @SerialName("miniapp")
    MINIAPP,
    @SerialName("contact")
    CONTACT,
    @SerialName("location")
    LOCATION
}

@Serializable
sealed class Segment<T> {
    abstract val type: SegmentType
    abstract val data: T
}

@Serializable
data class ForwardMessage(
    val type: Type = Type.Node,
    val data: Data
) {
    @Serializable
    enum class Type {
        @SerialName("node")
        Node
    }

    @Serializable
    data class Data(
        val userId: String,
        val nickname: String,
        val content: List<Segment<*>>
    )
}

@Serializable
@SerialName("poke")
data class PokeSegment(
    override val type: SegmentType = SegmentType.POKE,
    override val data: Data
) : Segment<PokeSegment.Data>() {
    @Serializable
    data class Data(
        val type: String,
        val id: String
    )
}

@Serializable
@SerialName("mface")
data class MFaceSegment(
    override val type: SegmentType = SegmentType.MFACE,
    override val data: Data
) : Segment<MFaceSegment.Data>() {
    @Serializable
    data class Data(
        val emojiPackageId: String,
        val id: String
    )
}

@Serializable
@SerialName("text")
data class TextSegment(
    override val type: SegmentType = SegmentType.TEXT,
    override val data: Data
) : Segment<TextSegment.Data>() {
    @Serializable
    data class Data(
        val text: String
    )
}

@Serializable
@SerialName("contact")
data class ContextSegment(
    override val type: SegmentType = SegmentType.CONTACT,
    override val data: Data
) : Segment<ContextSegment.Data>() {
    @Serializable
    data class Data(
        val type: Type,
        val id: String
    ) {
        @Serializable
        enum class Type {
            @SerialName("qq")
            QQ,
            @SerialName("group")
            GROUP
        }
    }
}

@Serializable
sealed interface FileData {
    val file: String
    val path: String?
    val thumb: String?
    val name: String?
    val url: String?
}

@Serializable
@SerialName("image")
data class ImageSegment(
    override val type: SegmentType = SegmentType.IMAGE,
    override val data: Data
) : Segment<ImageSegment.Data>() {
    @Serializable
    data class Data(
        val summary: String,
        val file: String,
        val subType: SubType,
        val url: String,
        val fileSize: String
    ) {
        @Serializable
        enum class SubType {
            @SerialName("0")
            IMAGE,
            @SerialName("1")
            FACE
        }
    }
}

@Serializable
@SerialName("voice")
data class RecordSegment(
    override val type: SegmentType = SegmentType.VOICE,
    override val data: Data
) : Segment<RecordSegment.Data>() {
    @Serializable
    data class Data(
        override val file: String,
        override val path: String?,
        override val thumb: String?,
        override val name: String?,
        override val url: String?
    ) : FileData
}

@Serializable
@SerialName("file")
data class FileSegment(
    override val type: SegmentType = SegmentType.FILE,
    override val data: Data
) : Segment<FileSegment.Data>() {
    @Serializable
    data class Data(
        override val file: String,
        override val path: String?,
        override val thumb: String?,
        override val name: String?,
        override val url: String?
    ) : FileData
}

@Serializable
@SerialName("video")
data class VideoSegment(
    override val type: SegmentType = SegmentType.VIDEO,
    override val data: Data
) : Segment<VideoSegment.Data>() {
    @Serializable
    data class Data(
        override val file: String,
        override val path: String?,
        override val thumb: String?,
        override val name: String?,
        override val url: String?
    ) : FileData
}

@Serializable
@SerialName("at")
data class AtSegment(
    override val type: SegmentType = SegmentType.AT,
    override val data: Data
) : Segment<AtSegment.Data>() {
    @Serializable
    data class Data(
        val qq: String
    )
}

@Serializable
@SerialName("reply")
data class ReplySegment(
    override val type: SegmentType = SegmentType.REPLY,
    override val data: Data
) : Segment<ReplySegment.Data>() {
    @Serializable
    data class Data(
        val qq: String,
        val name: String?
    )
}

@Serializable
@SerialName("face")
data class FaceSegment(
    override val type: SegmentType = SegmentType.FACE,
    override val data: Data
) : Segment<FaceSegment.Data>() {
    @Serializable
    data class Data(
        val id: String,
        val resultId: String?,
        val chainCount: Int?
    )
}

@Serializable
@SerialName("dice")
data class DiceSegment(
    override val type: SegmentType = SegmentType.DICE,
    override val data: Data
) : Segment<DiceSegment.Data>() {
    @Serializable
    data class Data(
        val result: String
    )
}

@Serializable
@SerialName("rps")
data class RPSSegment(
    override val type: SegmentType = SegmentType.RPS,
    override val data: Data
) : Segment<RPSSegment.Data>() {
    @Serializable
    data class Data(
        val result: String
    )
}

