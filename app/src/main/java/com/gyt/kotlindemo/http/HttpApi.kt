package com.gyt.kotlindemo.http

import com.gyt.kotlindemo.beans.MeiZiBean
import com.gyt.kotlindemo.beans.MyHttpResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author gyt
 * @date on 2018/11/27 11:51 AM
 * @describer TODO
 */
interface HttpApi{

    @GET("福利/10/{page}")
    fun getMeiZiPic(@Path("page") page: Int): Observable<MyHttpResponse<ArrayList<MeiZiBean>>>
}