package com.gyt.kotlindemo.ui.adapter

import android.content.Context
import com.gyt.kotlindemo.base.BaseAdapter
import com.gyt.kotlindemo.base.BaseViewHolder
import com.gyt.kotlindemo.beans.MeiZiBean
import com.gyt.kotlindemo.loadUrl
import kotlinx.android.synthetic.main.item_home.view.*

/**
 * @author gyt
 * @date on 2018/11/27 11:08 AM
 * @describer TODO
 */
class HomeAdapter(context: Context, mData: ArrayList<MeiZiBean>, mLayoutId: Int) : BaseAdapter<MeiZiBean>(context, mData, mLayoutId){
    override fun bindData(holder: BaseViewHolder, data: MeiZiBean, position: Int) {
        holder.itemView.tv_name.text = data.who
        holder.itemView.iv_pic.loadUrl(mContext, data.url)
    }
}