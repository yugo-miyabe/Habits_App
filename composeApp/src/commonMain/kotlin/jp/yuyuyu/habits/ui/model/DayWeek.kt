package jp.yuyuyu.habits.ui.model

import androidx.compose.runtime.Composable
import habits.composeapp.generated.resources.Res
import habits.composeapp.generated.resources.friday
import habits.composeapp.generated.resources.monday
import habits.composeapp.generated.resources.saturday
import habits.composeapp.generated.resources.sunday
import habits.composeapp.generated.resources.tuesday
import habits.composeapp.generated.resources.wednesday
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

enum class DayWeek(
    private val dayKey: StringResource,
) {
    Sunday(dayKey = Res.string.sunday),
    Monday(dayKey = Res.string.monday),
    Tuesday(dayKey = Res.string.tuesday),
    Wednesday(dayKey = Res.string.wednesday),
    Thursday(dayKey = Res.string.tuesday),
    Friday(dayKey = Res.string.friday),
    Saturday(dayKey = Res.string.saturday);

    val label @Composable get() = stringResource(dayKey)
}