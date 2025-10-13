package jp.yuyuyu.habits.ui.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import habits.composeapp.generated.resources.Res
import habits.composeapp.generated.resources.friday
import habits.composeapp.generated.resources.monday
import habits.composeapp.generated.resources.saturday
import habits.composeapp.generated.resources.sunday
import habits.composeapp.generated.resources.tuesday
import habits.composeapp.generated.resources.wednesday
import jp.yuyuyu.habits.theme.AppTheme
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

enum class DayWeek(
    private val dayKey: StringResource,
    private val colorProvider: @Composable () -> Color
) {
    Sunday(dayKey = Res.string.sunday, colorProvider = { AppTheme.colors.sunday }),
    Monday(dayKey = Res.string.monday, colorProvider = { AppTheme.colors.black }),
    Tuesday(dayKey = Res.string.tuesday, colorProvider = { AppTheme.colors.black }),
    Wednesday(dayKey = Res.string.wednesday, colorProvider = { AppTheme.colors.black }),
    Thursday(dayKey = Res.string.tuesday, colorProvider = { AppTheme.colors.black }),
    Friday(dayKey = Res.string.friday, colorProvider = { AppTheme.colors.black }),
    Saturday(dayKey = Res.string.saturday, colorProvider = { AppTheme.colors.saturday });

    val label @Composable get() = stringResource(dayKey)
    val color @Composable get() = colorProvider()
}