package data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

@kotlinx.serialization.Serializable
internal data class SourceCodeErrorDto(
    val status: String,
    @SerializedName("request_id")
    val requestId: String,
    val errors: List<ErrorDto>,
) {
    companion object {
        fun getEmpty() = SourceCodeErrorDto(
            status = "empty",
            requestId = "",
            errors = emptyList()
        )
    }
}

@kotlinx.serialization.Serializable
internal data class ErrorDto(
    val type: String,
    val message: String,
)
