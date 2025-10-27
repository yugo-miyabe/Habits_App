package jp.yuyuyu.habits.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.yuyuyu.habits.database.AppDatabase
import jp.yuyuyu.habits.database.HabitDataEntity
import jp.yuyuyu.habits.database.HabitDay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class HomeViewModel(
    appDatabase: AppDatabase
) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            appDatabase.getDao().insert(
                HabitDataEntity(
                    title = "Sample Habit",
                    calendar = listOf(
                        HabitDay(date = "2024-01-01", isSelected = true),
                        HabitDay(date = "2024-01-02", isSelected = true)
                    )
                )
            )
        }
    }
}
