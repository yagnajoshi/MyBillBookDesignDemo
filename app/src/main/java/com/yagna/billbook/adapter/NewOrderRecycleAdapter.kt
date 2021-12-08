package com.yagna.billbook.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.yagna.billbook.R
import com.yagna.billbook.BR
import com.yagna.billbook.databinding.ItemCardNewOrdersBinding
import com.yagna.billbook.model.NewOrderDataModel

/**
 * This Adapter class is used to display total orders
 *
 * @author Yagna Joshi
 */
class NewOrderRecycleAdapter(private val list: ArrayList<NewOrderDataModel>) : RecyclerView.Adapter<NewOrderRecycleAdapter.ViewHolder>() {


    /**
     *
     *This method is returning the view for each item in the list
     *  @author Yagna Joshi
     *  @param parent
     *  @param ViewType
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemCardNewOrdersBinding = DataBindingUtil.inflate( LayoutInflater.from(parent.context),
            R.layout.item_card_new_orders, parent, false)
        return ViewHolder(binding)
    }

    /*This method is giving the size of the list*/
    override fun getItemCount(): Int { return list.size }

    /**
     *This view holder class holds the  view items
     *
     *  @author Yagna Joshi
     *  @param itemRowBinding
     */
    class ViewHolder(var itemRowBinding: ItemCardNewOrdersBinding) : RecyclerView.ViewHolder(itemRowBinding.root) {
        /* bind() - This function will be bind the Item data to the variable @model of the view */
        fun bind(obj: Any?) {
            itemRowBinding.setVariable(BR.model, obj)
            itemRowBinding.executePendingBindings()
        }
    }

    /**
     *Binding row item's data to the  view of the Item
     *
     *  @author Yagna Joshi
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataDataModel: NewOrderDataModel = list[position]
        holder.bind(dataDataModel)
    }


}
