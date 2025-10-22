package jp.yuyuyu.habits.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.yuyuyu.habits.database.AppDatabase
import jp.yuyuyu.habits.database.HabitDataEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class HomeViewModel(
    appDatabase: AppDatabase
) : ViewModel() {

    init {
        println("HomeViewModel")
        viewModelScope.launch(Dispatchers.IO) {
            appDatabase.getDao().insert(
                HabitDataEntity(
                    id = 0,
                    title = "Sample Habit",
                )
            )
        }
    }
}
