package jp.yuyuyu.habits.ui.model

import androidx.compose.runtime.Composable
import habits.composeapp.generated.resources.Res
import habits.composeapp.generated.resources.dialog_database_error
import habits.composeapp.generated.resources.dialog_network_error
import org.jetbrains.compose.resources.stringResource

sealed class DialogType {
    abstract val title: String
    abstract val message: String
    abstract val onDismiss: () -> Unit

    data class DatabaseError(
        override val title: String,
        override val message: String,
        override val onDismiss: () -> Unit
    ) : DialogType()

    data class NetworkError(
        override val title: String,
        override val message: String,
        override val onDismiss: () -> Unit
    ) : DialogType()
}

internal val DialogType.localizedTitle: String
    @Composable get() = stringResource(
        resource = when (this) {
            is DialogType.DatabaseError -> Res.string.dialog_database_error
            is DialogType.NetworkError -> Res.string.dialog_network_error
        }
    )

internal val DialogType.localizedMessage: String
    @Composable get() = stringResource(
        resource = when (this) {
            is DialogType.DatabaseError -> Res.string.dialog_database_error
            is DialogType.NetworkError -> Res.string.dialog_network_error
        }
    )
