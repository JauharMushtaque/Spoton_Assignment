package com.example.spotonassignment.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.RecyclerView
import com.example.spotonassignment.common.AppUtils
import com.example.spotonassignment.R
import com.example.spotonassignment.databinding.CryptoItemLayoutBinding;
import com.example.spotonassignment.entities.Data

class CryptoCurrencyAdapter : RecyclerView.Adapter<CryptoCurrencyAdapter.CryptoViewHolder>() {
    private var dataList: List<Data> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder {
        val binding = CryptoItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CryptoViewHolder(binding)
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

    class CryptoViewHolder constructor(private val itemBinding: CryptoItemLayoutBinding):RecyclerView.ViewHolder(itemBinding.root){
        val serial_no = itemBinding.serialNo
        val name = itemBinding.name
        val timings = itemBinding.timings
        val price_dollar = itemBinding.price

        @SuppressLint("SetTextI18n")
        fun bind(data: Data){
            serial_no.setText(data.rank)
            name.setText(data.name)
            if(data.changePercent24Hr.toString().startsWith("-")){
                timings.setTextColor(getColor(itemView.context, R.color.pink_dark))
                price_dollar.setTextColor(getColor(itemView.context, R.color.pink_dark))
                timings.setText(data.changePercent24Hr?.let { AppUtils.roundToTwodigitsAfterPoint(it) }+"%")
            }else{
                timings.setText(data.changePercent24Hr?.let { "+"+ AppUtils.roundToTwodigitsAfterPoint(it) }+"%")
                timings.setTextColor(getColor(itemView.context, R.color.green))
                price_dollar.setTextColor(getColor(itemView.context, R.color.green))
            }

            price_dollar.setText("$"+data.priceUsd?.let { AppUtils.roundToTwodigitsAfterPoint(it) })
        }
    }

    override fun getItemCount() = dataList.size
}