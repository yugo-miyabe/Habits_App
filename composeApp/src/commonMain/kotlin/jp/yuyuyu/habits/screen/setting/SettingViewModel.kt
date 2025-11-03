package jp.yuyuyu.habits.screen.setting

import androidx.lifecycle.ViewModel

class SettingViewModel : ViewModel() {

    init {
        println("SettingViewModel")
    }

    fun onDataDeleteClick() {
        println("Data delete clicked")
    }
}
