package com.gyt.kotlindemo.ui.activity

import android.Manifest
import android.content.Intent
import android.support.v7.widget.GridLayoutManager
import com.gyt.kotlindemo.R
import com.gyt.kotlindemo.base.BaseActivity
import com.gyt.kotlindemo.beans.Constants
import com.gyt.kotlindemo.beans.MeiZiBean
import com.gyt.kotlindemo.http.ExceptionHandle
import com.gyt.kotlindemo.http.HttpManager
import com.gyt.kotlindemo.http.RxUtils
import com.gyt.kotlindemo.toast
import com.gyt.kotlindemo.ui.adapter.HomeAdapter
import kotlinx.android.synthetic.main.activity_main.*
import pub.devrel.easypermissions.EasyPermissions

class MainActivity : BaseActivity() {
    private var homeAdapter: HomeAdapter? = null
    private var mMeiZiList: ArrayList<MeiZiBean> = ArrayList<MeiZiBean>()

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun initView() {
        setToolbarTitle(R.string.app_name)
        checkPermissions()

        homeAdapter = HomeAdapter(this, mMeiZiList, R.layout.item_home)
        rcv_list.adapter = homeAdapter
        rcv_list.layoutManager = GridLayoutManager(this, 2)

        val disposable = HttpManager.httpService.getMeiZiPic(1).compose(RxUtils.ioMainSchedule())
                .subscribe({
                    mMeiZiList = it.results
                    homeAdapter?.setData(it.results)
                }, { toast(ExceptionHandle.handleException(it)) })

        addSubscription(disposable)
    }

    override fun initEvent() {
        homeAdapter?.setOnItemClickListener {
            //            toast(it.who)
            val intent = Intent(this, PictureActivity::class.java)
            intent.putExtra(Constants.BUNDLE_PIC_URL, it.url)
            startActivity(intent)
        }
    }

    private fun checkPermissions() {
        val perms = arrayOf( Manifest.permission.WRITE_EXTERNAL_STORAGE)
        // *操作符会把数组或者集合展开成字符串
        EasyPermissions.requestPermissions(this, "KotlinDemo应用需要访问sdcard权限，请允许", 0, *perms)
    }
}


