package com.gyt.kotlindemo.beans

/**
 * @author gyt
 * @date on 2018/11/27 3:47 PM
 * @describer TODO
 */
data class MyHttpResponse<T>(val error: Boolean, val results: T)