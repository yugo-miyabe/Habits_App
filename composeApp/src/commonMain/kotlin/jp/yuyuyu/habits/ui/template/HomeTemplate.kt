package jp.yuyuyu.habits.ui.template

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import habits.composeapp.generated.resources.Res
import habits.composeapp.generated.resources.compose_multiplatform
import jp.yuyuyu.habits.ui.organisms.TopBar
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun HomeTemplate(
    onSettingClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopBar(
                actions = {
                    IconButton(onClick = onSettingClick) {
                        Icon(
                            painter = painterResource(Res.drawable.compose_multiplatform),
                            contentDescription = null,
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Text(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxHeight(),
            text = "Home"
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun HomeTemplatePreview() {
    HomeTemplate(
        onSettingClick = { /* preview */ }
    )
}