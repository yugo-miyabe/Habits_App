package jp.yuyuyu.habits.ui.template

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import habits.composeapp.generated.resources.Res
import habits.composeapp.generated.resources.licence
import jp.yuyuyu.habits.ui.organisms.ListItemCell
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SettingTemplate() {
    Scaffold { innerPadding ->
        Column {
            ListItemCell(
                title = stringResource(Res.string.licence)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun SettingTemplate_Preview() {
    SettingTemplate()
}
