package com.gyt.kotlindemo.http

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @author gyt
 * @date on 2018/11/27 2:42 PM
 * @describer io -> main 的scheduler
 */
class IoMainScheduler<T> : BaseScheduler<T>(Schedulers.io(), AndroidSchedulers.mainThread())