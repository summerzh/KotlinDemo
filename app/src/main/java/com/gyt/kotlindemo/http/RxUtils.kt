package com.gyt.kotlindemo.http

/**
 * @author gyt
 * @date on 2018/11/27 2:28 PM
 * @describer TODO
 */
object RxUtils {

    fun <T> ioMainSchedule(): IoMainScheduler<T> {
        return IoMainScheduler()
    }
}