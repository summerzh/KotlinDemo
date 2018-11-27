package com.gyt.kotlindemo.http

/**
 * @author gyt
 * @date on 2018/11/27 3:51 PM
 * @describer TODO
 */
//class HandleResultHelper<T> : ObservableTransformer<MyHttpResponse<T>, T>,
//        FlowableTransformer<MyHttpResponse<T>, T>,
//        SingleTransformer<MyHttpResponse<T>, T>,
//        MaybeTransformer<MyHttpResponse<T>, T>,
//        CompletableTransformer {
//
//
//    override fun apply(upstream: Observable<MyHttpResponse<T>>): ObservableSource<T> {
//        return die@ upstream.flatMap<T> {
//            val result = it.result
//            if(it.error.not()){
//                return@die Observable.create<T> {
//                    if (result == null) {
//                        it.onComplete()
//                    }
//                    try {
//                        it.onNext(result)
//                        it.onComplete()
//                    } catch (e: Exception) {
//                        it.onError(e)
//                    }
//                }
//            }else{
//                return@die Observable.error(HttpException("fsdfs"))
//            }
//
//        }
//    }
//
//    override fun apply(upstream: Flowable<T>): Publisher<T> {
//
//    }
//
//    override fun apply(upstream: Single<T>): SingleSource<T> {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//    override fun apply(upstream: Maybe<T>): MaybeSource<T> {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//    override fun apply(upstream: Completable): CompletableSource {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//
//}