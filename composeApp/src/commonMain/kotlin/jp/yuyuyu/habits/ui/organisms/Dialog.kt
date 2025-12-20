package jp.yuyuyu.habits.ui.organisms

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import habits.composeapp.generated.resources.Res
import habits.composeapp.generated.resources.ok
import jp.yuyuyu.habits.theme.AppTheme
import jp.yuyuyu.habits.ui.model.DialogType
import jp.yuyuyu.habits.ui.model.localizedMessage
import jp.yuyuyu.habits.ui.model.localizedTitle
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CommonDialog(
    onDismiss: () -> Unit,
    dialogType: DialogType,
    modifier: Modifier = Modifier,
) {
    Dialog(
        onDismissRequest = onDismiss
    ) {
        Card(
            modifier = modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = dialogType.localizedTitle,
                    style = AppTheme.typography.titleLarge,
                    modifier = Modifier.fillMaxWidth()
                )

                Text(
                    text = dialogType.localizedMessage,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp)
                )

                HorizontalDivider()

                Button(
                    onClick = onDismiss,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    content = {
                        Text(text = stringResource(Res.string.ok))
                    }
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun CommonDialogPreview() {
    CommonDialog(
        onDismiss = { /* preview */ },
        dialogType = DialogType.DatabaseError
    )
}
