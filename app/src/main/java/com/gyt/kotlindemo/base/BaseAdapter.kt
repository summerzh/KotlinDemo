package com.gyt.kotlindemo.base

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * @author gyt
 * @date on 2018/11/27 10:19 AM
 * @describer TODO
 */
abstract class BaseAdapter<T>(var mContext: Context, var mData: ArrayList<T>, private var mLayoutId: Int): RecyclerView.Adapter<BaseViewHolder>(){
    private var mInflater: LayoutInflater? = null
    private var mOnClickListener: ((T) -> Unit)? = null

    init {
        mInflater = LayoutInflater.from(mContext)
    }

    override fun onCreateViewHolder(parent: ViewGroup, itemType: Int): BaseViewHolder {
        val itemView = mInflater?.inflate(mLayoutId, parent, false)
        return BaseViewHolder(itemView!!)// 强行不为空
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        bindData(holder, mData[position], position)
        holder.itemView.setOnClickListener { mOnClickListener?.invoke(mData[position]) }
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    abstract fun bindData(holder: BaseViewHolder, data: T, position: Int)

    /**
     * 设置数据
     */
    fun setData(data: ArrayList<T>){
        mData.clear()
        mData = data
        notifyDataSetChanged()
    }

    // 高阶函数
    fun setOnItemClickListener(onClickListener: (T) -> Unit) {
        mOnClickListener = onClickListener
    }
}