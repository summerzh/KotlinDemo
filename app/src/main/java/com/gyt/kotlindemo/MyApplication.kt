package com.gyt.kotlindemo

import android.app.Application
import android.content.Context
import kotlin.properties.Delegates

/**
 * @author gyt
 * @date on 2018/11/28 9:56 AM
 * @describer TODO
 */
class MyApplication: Application() {

    companion object {
        // 属性被限制成不为null，否则报错
        var context: Context by Delegates.notNull()
    }
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}