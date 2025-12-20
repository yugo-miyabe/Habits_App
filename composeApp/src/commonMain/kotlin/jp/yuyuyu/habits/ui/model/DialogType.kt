package jp.yuyuyu.habits.ui.model

import androidx.compose.runtime.Composable
import habits.composeapp.generated.resources.Res
import habits.composeapp.generated.resources.dialog_database_error
import habits.composeapp.generated.resources.dialog_database_error_message
import habits.composeapp.generated.resources.dialog_network_error
import habits.composeapp.generated.resources.dialog_network_error_message
import org.jetbrains.compose.resources.stringResource

sealed class DialogType {
    object DatabaseError : DialogType()
    object NetworkError : DialogType()
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
            is DialogType.DatabaseError -> Res.string.dialog_database_error_message
            is DialogType.NetworkError -> Res.string.dialog_network_error_message
        }
    )
