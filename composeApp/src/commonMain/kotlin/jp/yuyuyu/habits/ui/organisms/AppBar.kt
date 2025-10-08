package jp.yuyuyu.habits.ui.organisms

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import habits.composeapp.generated.resources.Res
import habits.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    title: String? = null,
    actions: @Composable RowScope.() -> Unit = {},
) {
    TopAppBar(
        title = {
            if (!title.isNullOrEmpty()) {
                Text(
                    text = title,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.labelLarge
                )
            }
        },
        modifier = modifier,
        navigationIcon = @Composable {
            // TODO
        },
        actions = actions
    )
}

@Preview
@Composable
private fun TopBar_Preview() {
    TopBar(actions = {
        IconButton(onClick = { /* preview */ }) {
            Icon(
                painter = painterResource(Res.drawable.compose_multiplatform),
                contentDescription = null,
            )
        }
    })
}

