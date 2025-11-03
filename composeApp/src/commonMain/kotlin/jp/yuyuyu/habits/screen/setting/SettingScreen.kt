package jp.yuyuyu.habits.screen.setting

import androidx.compose.runtime.Composable
import jp.yuyuyu.habits.ui.template.SettingTemplate
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SettingScreen(
    onLicenseInfoClick: () -> Unit,
    viewModel: SettingViewModel = koinViewModel()
) {

    SettingTemplate(
        onLicenseInfoClick = onLicenseInfoClick,
        onDataDeleteClick = {
            viewModel.onDataDeleteClick()
        }
    )
}
