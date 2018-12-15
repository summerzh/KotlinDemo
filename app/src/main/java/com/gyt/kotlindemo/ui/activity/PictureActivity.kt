package com.gyt.kotlindemo.ui.activity

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import com.bumptech.glide.Glide
import com.gyt.kotlindemo.R
import com.gyt.kotlindemo.base.BaseActivity
import com.gyt.kotlindemo.beans.Constants
import com.gyt.kotlindemo.http.ApiException
import com.gyt.kotlindemo.http.ExceptionHandle
import com.gyt.kotlindemo.http.RxUtils
import com.gyt.kotlindemo.loadUrl
import com.gyt.kotlindemo.toast
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_picture.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


/**
 * @author gyt
 * @date on 2018/12/14 5:56 PM
 * @describer picture大图预览页面
 */

class PictureActivity : BaseActivity() {
    private var picUrl: String? = null
    override fun getLayoutId(): Int = R.layout.activity_picture
    override fun initView() {
    }

    override fun initEvent() {
        setToolbarTitle(R.string.title_picture_preview)
        picUrl = intent.getStringExtra(Constants.BUNDLE_PIC_URL)
        iv_pic.loadUrl(this, picUrl!!)
        iv_pic.setOnLongClickListener { picLongClicked() }
    }

    private fun picLongClicked(): Boolean {
        AlertDialog.Builder(this)
                .setTitle(R.string.dialog_title)
                .setMessage(R.string.dialog_message)
                .setPositiveButton(R.string.dialog_position_button_desc, { _, _ ->
                    saveImageToGallery()
                })
                .setNegativeButton(R.string.dialog_negative_button_desc, null)
                .create()
                .show()

        return true
    }

    private fun saveImageToGallery() {
        Observable.create<Bitmap> {
            val futureTarget = Glide.with(this)
                    .asBitmap()
                    .load(picUrl)
                    .submit(iv_pic.width, iv_pic.height)

            val bitmap = futureTarget.get()
            Glide.with(this).clear(futureTarget)

            if (bitmap == null) {
                it.onError(ApiException("无法下载图片"))
            }
            it.onNext(bitmap)
            it.onComplete()
        }.flatMap {
            val appDir = File(Environment.getExternalStorageDirectory(), "Meizhi")
            if (!appDir.exists()) {
                appDir.mkdir()
            }
            val fileName = picUrl!!.replace("/", "_")
            val file = File(appDir, fileName)
            try {
                val outputStream = FileOutputStream(file)
                it.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                outputStream.flush()
                outputStream.close()
            } catch (e: IOException) {
                e.printStackTrace()
                return@flatMap Observable.error<String>(ApiException("下载失败"))
            }

            val uri = Uri.fromFile(file)
            // 通知图库更新
            val scannerIntent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri)
            sendBroadcast(scannerIntent)
            return@flatMap Observable.just(fileName)
        }.compose(RxUtils.ioMainSchedule())
                .subscribe({
                    toast("图片下载成功: $it")
                }, { toast(ExceptionHandle.handleException(it)) })

    }

}