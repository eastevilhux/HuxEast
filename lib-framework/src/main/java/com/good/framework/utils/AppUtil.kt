package com.good.framework.utils

import android.app.Application
import android.content.pm.ApplicationInfo

final class AppUtil {
    companion object{
        fun isDebug(info: ApplicationInfo): Boolean {
            return info.flags and ApplicationInfo.FLAG_DEBUGGABLE != 0
        }
    }


}