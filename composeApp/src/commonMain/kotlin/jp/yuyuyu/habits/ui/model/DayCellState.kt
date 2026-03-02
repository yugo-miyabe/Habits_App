package jp.yuyuyu.habits.ui.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import jp.yuyuyu.habits.theme.AppTheme

@Suppress("unused")
enum class DayCellState {
    NORMAL,
    TODAY,
    HABIT,
    TODAY_AND_HABIT;

    companion object {
        fun from(isToday: Boolean, isHabitDay: Boolean): DayCellState = when {
            isToday && isHabitDay -> TODAY_AND_HABIT
            isToday -> TODAY
            isHabitDay -> HABIT
            else -> NORMAL
        }
    }
}

internal val DayCellState.backgroundColor: Color
    @Composable get() = when (this) {
        DayCellState.NORMAL -> Color.Transparent
        DayCellState.TODAY -> AppTheme.colors.isTodayBackGround
        DayCellState.HABIT -> AppTheme.colors.pinkBackground
        DayCellState.TODAY_AND_HABIT -> AppTheme.colors.pinkBackground
    }

internal val DayCellState.borderColor: Color
    @Composable get() = when (this) {
        DayCellState.NORMAL -> Color.Transparent
        DayCellState.TODAY -> AppTheme.colors.isTodayBorder
        DayCellState.HABIT -> AppTheme.colors.textPinkFont
        DayCellState.TODAY_AND_HABIT -> AppTheme.colors.isTodayBorder
    }

internal val DayCellState.textColor: Color
    @Composable get() = when (this) {
        DayCellState.NORMAL -> AppTheme.colors.black
        DayCellState.TODAY -> AppTheme.colors.black
        DayCellState.HABIT -> AppTheme.colors.black
        DayCellState.TODAY_AND_HABIT -> AppTheme.colors.black
    }
