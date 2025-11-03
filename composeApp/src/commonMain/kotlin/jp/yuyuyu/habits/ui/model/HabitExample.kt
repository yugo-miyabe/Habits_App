package jp.yuyuyu.habits.ui.model

import habits.composeapp.generated.resources.Res
import habits.composeapp.generated.resources.habit_strength
import org.jetbrains.compose.resources.StringResource

enum class HabitExample(
    private val resource: StringResource,
) {
    Strength(resource = Res.string.habit_strength),
}
