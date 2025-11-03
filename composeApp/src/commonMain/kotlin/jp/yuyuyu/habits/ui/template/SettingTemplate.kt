package jp.yuyuyu.habits.ui.template

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import habits.composeapp.generated.resources.Res
import habits.composeapp.generated.resources.add_habits
import habits.composeapp.generated.resources.delete
import habits.composeapp.generated.resources.licence
import habits.composeapp.generated.resources.ver
import jp.yuyuyu.habits.getPlatform
import jp.yuyuyu.habits.theme.AppTheme
import jp.yuyuyu.habits.ui.organisms.ListItemCell
import jp.yuyuyu.habits.ui.organisms.TopBar
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SettingTemplate(
    onBackClick: () -> Unit,
    onLicenseInfoClick: () -> Unit,
    onDataDeleteClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(Res.string.add_habits),
                onBackClick = onBackClick,
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(vertical = 16.dp),
        ) {
            ListItemCell(
                title = stringResource(Res.string.licence),
                onClick = onLicenseInfoClick
            )

            ListItemCell(
                title = stringResource(Res.string.delete),
                textColor = AppTheme.colors.red,
                onClick = onDataDeleteClick
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = stringResource(Res.string.ver, getPlatform().version),
                style = AppTheme.typography.labelMediumBold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun SettingTemplate_Preview() {
    SettingTemplate(
        onBackClick = { /* preview */ },
        onLicenseInfoClick = { /* preview */ },
        onDataDeleteClick = { /* preview */ }
    )
}
