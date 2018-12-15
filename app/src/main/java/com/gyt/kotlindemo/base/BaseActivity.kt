package com.gyt.kotlindemo.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.orhanobut.logger.Logger
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.view_toolbar.*
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions



/**
 * @author gyt
 * @date on 2018/11/27 9:52 AM
 * @describer TODO
 */
abstract class BaseActivity : AppCompatActivity(), EasyPermissions.PermissionCallbacks {

    private var compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initView()
        initEvent()
    }

    abstract fun getLayoutId(): Int

    abstract fun initView()

    abstract fun initEvent()

    fun addSubscription(disposable: Disposable){
        compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        if(!compositeDisposable.isDisposed){
            compositeDisposable.clear()
        }
    }

    /**
     * 设置toolbar标题
     */
    protected fun setToolbarTitle(stringRes: Int){
        tv_toolbar_title?.text = getString(stringRes)
    }

    /**
     * 设置toolbar标题
     */
    protected fun setToolbarTitle(title: String){
        tv_toolbar_title?.text = title
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onPermissionsGranted(requestCode: Int, list: List<String>) {
        Logger.i("onPermissionsGranted: $requestCode : $list")
    }

    override fun onPermissionsDenied(requestCode: Int, list: List<String>) {
        Logger.i("onPermissionsDenied: $requestCode : $list")
        val sb = StringBuilder()
        for (param in list) {
            sb.append(param).append("\n")
        }
        val desc = sb.replace(sb.length - 2, sb.length, "")
        // 用户点击不再询问时执行
        if(EasyPermissions.somePermissionPermanentlyDenied(this, list)){
            AppSettingsDialog.Builder(this)
                    .setRationale("此功能需要${desc}权限，否则无法使用，是否打开设置")
                    .setPositiveButton("好")
                    .setNegativeButton("不行")
                    .build()
                    .show()
        }
    }
}