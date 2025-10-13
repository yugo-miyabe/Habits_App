package jp.yuyuyu.habits

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

@Composable
actual fun AdMobBanner(
    modifier: Modifier,
) {
    val context = LocalContext.current

    AndroidView(
        modifier = modifier.fillMaxWidth(),
        factory = {
            AdView(context).apply {
                this.adUnitId = BuildConfig.BANNER_AD_UNIT_ID
                this.setAdSize(AdSize.BANNER)
                this.loadAd(AdRequest.Builder().build())
            }
        }
    )
}
