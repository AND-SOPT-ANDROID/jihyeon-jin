package org.sopt.and.core.composition

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import org.sopt.and.core.utils.PreferenceUtil

@Composable
fun PreferenceUtilProvider(
    preferenceUtil: PreferenceUtil,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        PreferenceUtil.LocalPreferenceUtils provides preferenceUtil
    ) {
        content()
    }
}