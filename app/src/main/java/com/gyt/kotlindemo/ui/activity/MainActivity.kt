package com.gyt.kotlindemo.ui.activity

import com.gyt.kotlindemo.ui.adapter.HomeAdapter
import com.gyt.kotlindemo.R
import com.gyt.kotlindemo.base.BaseActivity
import com.gyt.kotlindemo.beans.MeiZiBean
import com.gyt.kotlindemo.http.ExceptionHandle
import com.gyt.kotlindemo.http.HttpManager
import com.gyt.kotlindemo.http.RxUtils
import com.gyt.kotlindemo.toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    private var homeAdapter: HomeAdapter? = null
    private var mMeiZiList: ArrayList<MeiZiBean> = ArrayList<MeiZiBean>()

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun initView() {
        homeAdapter = HomeAdapter(this, mMeiZiList, R.layout.item_home)
        rcv_list.adapter = homeAdapter

        val disposable = HttpManager.httpService.getMeiZiPic(1).compose(RxUtils.ioMainSchedule())
                .subscribe({
                    mMeiZiList = it.results
                    homeAdapter?.setData(it.results)
                }, { ExceptionHandle.handleException(it) })

        addSubscription(disposable)
    }

    override fun initEvent() {
        homeAdapter?.setOnItemClickListener {
            toast(it.who)
        }
    }
}

