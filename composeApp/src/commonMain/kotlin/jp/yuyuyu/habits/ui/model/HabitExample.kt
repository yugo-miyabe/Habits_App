package jp.yuyuyu.habits.ui.model

import androidx.compose.runtime.Composable
import habits.composeapp.generated.resources.Res
import habits.composeapp.generated.resources.habit_cleaning
import habits.composeapp.generated.resources.habit_cooking
import habits.composeapp.generated.resources.habit_diary
import habits.composeapp.generated.resources.habit_early_rise
import habits.composeapp.generated.resources.habit_english_learning
import habits.composeapp.generated.resources.habit_exercise
import habits.composeapp.generated.resources.habit_gratitude
import habits.composeapp.generated.resources.habit_hydration
import habits.composeapp.generated.resources.habit_meditation
import habits.composeapp.generated.resources.habit_pilates
import habits.composeapp.generated.resources.habit_reading
import habits.composeapp.generated.resources.habit_reduce_phone
import habits.composeapp.generated.resources.habit_strength
import habits.composeapp.generated.resources.habit_stretching
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

enum class HabitExample(
    private val resource: StringResource,
) {
    Strength(resource = Res.string.habit_strength),
    EarlyRise(resource = Res.string.habit_early_rise),
    Exercise(resource = Res.string.habit_exercise),
    Reading(resource = Res.string.habit_reading),
    Meditation(resource = Res.string.habit_meditation),
    Diary(resource = Res.string.habit_diary),
    Hydration(resource = Res.string.habit_hydration),
    ReducePhone(resource = Res.string.habit_reduce_phone),
    EnglishLearning(resource = Res.string.habit_english_learning),
    Pilates(resource = Res.string.habit_pilates),
    Stretching(resource = Res.string.habit_stretching),
    Cooking(resource = Res.string.habit_cooking),
    Cleaning(resource = Res.string.habit_cleaning),
    Gratitude(resource = Res.string.habit_gratitude);

    val label @Composable get() = stringResource(resource)
}
