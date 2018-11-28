package com.gyt.kotlindemo

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.gyt.kotlindemo.glide.GlideApp

/**
 * @author gyt
 * @date on 2018/11/28 9:50 AM
 * @describer 扩展函数
 */

fun ImageView.loadUrl(context: Context, url: String) {
    GlideApp.with(context)
            .load(url)
            .into(this)
}

fun Activity.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT){
    Toast.makeText(MyApplication.context, message, duration).show()
}

fun View.dip2px(dipValue: Float): Int {
    val scale = this.resources.displayMetrics.density
    return (dipValue * scale + 0.5f).toInt()
}

fun View.px2dip(pxValue: Float): Int {
    val scale = this.resources.displayMetrics.density
    return (pxValue / scale + 0.5f).toInt()
}