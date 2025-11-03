package jp.yuyuyu.habits.screen.license

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.mikepenz.aboutlibraries.ui.compose.m3.LibrariesContainer
import com.mikepenz.aboutlibraries.ui.compose.produceLibraries
import habits.composeapp.generated.resources.Res
import habits.composeapp.generated.resources.licence
import jp.yuyuyu.habits.ui.organisms.TopBar
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun LicenseInfoScreen(
    onBackClick: () -> Unit
) {
    val libraries by produceLibraries {
        Res.readBytes("files/aboutlibraries.json").decodeToString()
    }

    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(Res.string.licence),
                onBackClick = onBackClick,
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            libraries?.let {
                LibrariesContainer(
                    libraries = libraries,
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun LicenseInfo_Preview() {
    LicenseInfoScreen(
        onBackClick = { /* preview */ }
    )
}
