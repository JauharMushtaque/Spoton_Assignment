package com.example.spotonassignment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.spotonassignment.model.Data
import kotlinx.android.synthetic.main.crypto_item_layout.view.*

class CryptoCurrencyAdapter : RecyclerView.Adapter<CryptoCurrencyAdapter.CryptoViewHolder>() {
    private var dataList: List<Data> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder {
        return CryptoViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.crypto_item_layout, parent, false))
    }

    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {
        when(holder){
            is CryptoViewHolder -> {
                holder.bind(dataList.get(position))
            }
        }
    }

    fun updateData(data: List<Data>){
        dataList = data
        notifyDataSetChanged()
    }

    class CryptoViewHolder constructor(itemView: View):RecyclerView.ViewHolder(itemView){
        val serial_no = itemView.serial_no
        val name = itemView.name
        val timings = itemView.timings
        val price_dollar = itemView.price

        @SuppressLint("ResourceAsColor")
        fun bind(data: Data){
            serial_no.setText(data.rank)
            name.setText(data.name)
            if(data.changePercent24Hr.toString().startsWith("-")){
//                timings.setTextColor(android.R.color.holo_red_dark)
//                price_dollar.setTextColor(android.R.color.holo_red_dark)
                timings.setText(data.changePercent24Hr?.let { AppUtils.roundToTwodigitsAfterPoint(it) }+"%")
            }else{
                timings.setText(data.changePercent24Hr?.let { "+"+AppUtils.roundToTwodigitsAfterPoint(it) }+"%")
//                timings.setTextColor(android.R.color.holo_green_dark)
//                price_dollar.setTextColor(android.R.color.holo_green_dark)
            }

            price_dollar.setText("$"+data.priceUsd?.let { AppUtils.roundToTwodigitsAfterPoint(it) })
        }
    }

    override fun getItemCount() = dataList.size
}