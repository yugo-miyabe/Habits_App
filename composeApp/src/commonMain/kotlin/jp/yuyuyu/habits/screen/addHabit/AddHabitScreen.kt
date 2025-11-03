package jp.yuyuyu.habits.screen.addHabit

import androidx.compose.runtime.Composable
import jp.yuyuyu.habits.ui.template.AddHabitTemplate
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AddHabitScreen(
    onBackClick: () -> Unit,
    onAddHabitClick: () -> Unit,
    viewModel: AddHabitViewModel = koinViewModel()
) {
    AddHabitTemplate(
        onBackClick = onBackClick,
        onAddHabitClick = {
            // TODO 画面遷移タイミング修正
            viewModel.onAddHabit()
            onAddHabitClick()
        },
    )
}
