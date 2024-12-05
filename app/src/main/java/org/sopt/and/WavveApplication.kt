package org.sopt.and

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

//hilt 사용하려면 @HiltAndroidApp정의 -> 모든 작업 시작점
@HiltAndroidApp
class WavveApplication : Application()