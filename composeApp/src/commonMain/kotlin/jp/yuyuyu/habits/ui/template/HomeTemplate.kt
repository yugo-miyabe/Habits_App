package jp.yuyuyu.habits.ui.template

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import habits.composeapp.generated.resources.Res
import habits.composeapp.generated.resources.settings_24dp
import jp.yuyuyu.habits.AdMobBanner
import jp.yuyuyu.habits.ui.organisms.Calendar
import jp.yuyuyu.habits.ui.organisms.TopBar
import jp.yuyuyu.habits.util.CalendarUtil
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun HomeTemplate(
    onSettingClick: () -> Unit,
) {
    Scaffold(topBar = {
        TopBar(actions = {
            IconButton(onClick = onSettingClick) {
                Icon(
                    painter = painterResource(Res.drawable.settings_24dp),
                    contentDescription = null,
                )
            }
        })
    }) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
            Column {
                Calendar(
                    calendarWeekList = CalendarUtil.createMonthUIModels(),
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = "Home",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Box(modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter)) {
                AdMobBanner()
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun HomeTemplatePreview() {
    HomeTemplate(
        onSettingClick = { /* preview */ }
    )
}