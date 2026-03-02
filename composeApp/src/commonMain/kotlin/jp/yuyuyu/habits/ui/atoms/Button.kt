package jp.yuyuyu.habits.ui.atoms


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import habits.composeapp.generated.resources.Res
import habits.composeapp.generated.resources.add_24dp
import habits.composeapp.generated.resources.add_habits
import jp.yuyuyu.habits.theme.AppTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    backgroundColor: Color = AppTheme.colors.accentColor,
    contentColor: Color = AppTheme.colors.white,
    height: Dp = 56.dp,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .clip(RoundedCornerShape(height / 2))
            .background(if (enabled) backgroundColor else backgroundColor.copy(alpha = 0.4f))
            .clickable(enabled = enabled, role = Role.Button, onClick = onClick)
            .padding(horizontal = 24.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = contentColor,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
fun ExtendedFloatingActionButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    ExtendedFloatingActionButton(
        containerColor = if (enabled) {
            AppTheme.colors.accentColor
        } else {
            AppTheme.colors.accentColor.copy(0.4f)
        },
        contentColor = AppTheme.colors.white,
        onClick = {
            if (enabled) onClick()
        },
        icon = {
            Icon(
                painter = painterResource(Res.drawable.add_24dp),
                contentDescription = stringResource(Res.string.add_habits)
            )
        },
        text = {
            Text(
                text = stringResource(Res.string.add_habits),
                style = AppTheme.typography.titleMediumBold
            )
        },
        modifier = modifier
    )
}

@Composable
@Preview(showBackground = true)
private fun PrimaryButton_Preview() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PrimaryButton(
            text = "Primary Button",
            onClick = { /* preview */ }
        )
        PrimaryButton(
            text = "Disabled Button",
            onClick = { /* preview */ },
            enabled = false
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun ExtendedFloatingActionButton_Preview() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ExtendedFloatingActionButton(onClick = { /* preview */ })
        ExtendedFloatingActionButton(onClick = { /* preview */ }, enabled = false)
    }
}
