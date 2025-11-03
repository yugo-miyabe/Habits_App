package jp.yuyuyu.habits.screen.addHabit

import androidx.compose.runtime.Composable
import jp.yuyuyu.habits.ui.template.AddHabitTemplate
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun AddHabitScreen(
    onBackClick: () -> Unit,
    viewModel: AddHabitViewModel = koinViewModel()
) {
    AddHabitTemplate(
        onBackClick = onBackClick,
        onAddHabitClick = viewModel::onAddHabit
    )
}
