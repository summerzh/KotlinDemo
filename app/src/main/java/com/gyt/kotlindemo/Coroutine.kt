package com.gyt.kotlindemo

import android.os.Looper
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.UI

/**
 * @author gyt
 * @date on 2018/11/26 9:07 AM
 * @describer TODO
 */
class Coroutine {
    /**
     * fun launch(): Job
     * fun async(): Deferred
     *
     * 通常Android在用的时候都是传一个UI就表示在 UI 线程启动协程，
     * 或者传一个CommonPool表示在异步启动协程
     * 还有一个是Unconfined表示不指定，在哪个线程调用就在哪个线程执行。
     */
    fun run() {
        launch {
            delay(2000L)
            println("hello, world")
            val isUIThread = Thread.currentThread() == Looper.getMainLooper().thread
            println("UI::===$isUIThread")
        }

        runBlocking {
            delay(1000L)
            println("Hello, Kotlin")
        }

        // launch()方法会返回一个job对象，job对象常用的方法有三个，叫start、join和cancel。
        // 分别对应了协程的启动、切换至当前协程、取消
        val job = launch(UI, CoroutineStart.LAZY) {
            println("hello lazy")
        }

        job.start()


        launch {
            println("hello start")
            // 切换协程
            // join()方法就比较特殊，他是一个suspend方法。
            // suspend 修饰的方法(或闭包)只能调用被suspend修饰过的方法(或闭包)
            job.join()
            println("hello end")
        }



        val deferred1 = async(CommonPool) {
            "hello async1"
        }

        async(UI) {
            println("hello async2")
            // await()可以返回当前协程的执行结果
            println(deferred1.await())
        }
    }

    fun example() {
        //每秒输出两个数字
        val job1 = launch(Unconfined, CoroutineStart.LAZY) {
            var count = 0
            while (true) {
                val isUIThread = Thread.currentThread() == Looper.getMainLooper().thread
                println("UI::===$isUIThread")
                count++
                //delay()表示将这个协程挂起500ms
                delay(500)
                println("count::$count")
            }
        }

        //job2会立刻启动
        val job2 = async(CommonPool) {
            job1.start()
            "ZhangTao"
        }

        launch(UI) {
            delay(3000)
            job1.cancel()
            //await()的规则是：如果此刻job2已经执行完则立刻返回结果，否则等待job2执行
            println(job2.await())
        }
    }
}



