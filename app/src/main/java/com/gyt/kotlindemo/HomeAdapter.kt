package com.gyt.kotlindemo

import android.content.Context
import com.gyt.kotlindemo.base.BaseAdapter
import com.gyt.kotlindemo.base.BaseViewHolder
import com.gyt.kotlindemo.beans.MeiZiBean
import com.gyt.kotlindemo.glide.GlideApp
import kotlinx.android.synthetic.main.item_home.view.*

/**
 * @author gyt
 * @date on 2018/11/27 11:08 AM
 * @describer TODO
 */
class HomeAdapter(context: Context, mData: ArrayList<MeiZiBean>, mLayoutId: Int) : BaseAdapter<MeiZiBean>(context, mData, mLayoutId){
    override fun bindData(holder: BaseViewHolder, data: MeiZiBean, position: Int) {
        holder.itemView.tv_name.text = data.who
        GlideApp.with(mContext)
                .load(data.url)
                .into(holder.itemView.iv_pic)
    }
}