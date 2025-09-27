package jp.yuyuyu.habits.ui.template

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SettingTemplate() {
    Scaffold { innerPadding ->
        Text(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxHeight(),
            text = "SettingTemplate"
        )
    }
}