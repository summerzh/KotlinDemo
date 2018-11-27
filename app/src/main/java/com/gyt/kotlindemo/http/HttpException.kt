package com.gyt.kotlindemo.http


/**
 * @author gyt
 * @date on 2018/11/27 4:07 PM
 * @describer TODO
 */
class HttpException : RuntimeException {
    private var code: Int? = null


    constructor(throwable: Throwable, code: Int) : super(throwable) {
        this.code = code
    }

    constructor(message: String) : super(Throwable(message))
}