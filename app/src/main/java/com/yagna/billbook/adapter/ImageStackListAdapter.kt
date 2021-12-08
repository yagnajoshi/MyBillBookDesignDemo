package com.yagna.billbook.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.yagna.billbook.R
import de.hdodenhof.circleimageview.CircleImageView

/**
 * This Adapter class is used to display Profile Avatars with overlap effect
 *
 * @author Yagna Joshi
 */
class ImageStackListAdapter(
    val list: ArrayList<Int>,
    val indexSubs: HomeViewPagerAdapter.PageType
) : RecyclerView.Adapter<ImageStackListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return if (viewType == 1) {
            val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_card_stack_more, parent, false)
            ViewHolder(v)
        } else {
            val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_card_stack_list, parent, false)
            ViewHolder(v)
        }

    }


    /**
     * This method returns total count of the Items
     *
     * @author Yagna Joshi
     */
    override fun getItemCount(): Int {
        return if (list.size > 3) 4 else list.size
    }


    class ViewHolder(var itemRowBinding: View) : RecyclerView.ViewHolder(itemRowBinding) {
        fun bind(obj: Int, indexSubs: HomeViewPagerAdapter.PageType) {
            val imgView = itemRowBinding.findViewById<CircleImageView>(R.id.img)
            imgView.setImageResource(obj)
            when (indexSubs) {
                HomeViewPagerAdapter.PageType.INDEX_SUBS -> {
                    imgView.borderColor = Color.parseColor("#234DDC")
                }
                HomeViewPagerAdapter.PageType.INDEX_CUSTOMERS -> {
                    imgView.borderColor = Color.parseColor("#33A1CC")
                }
                HomeViewPagerAdapter.PageType.INDEX_ACITVE_CUSTOMERS -> {
                    imgView.borderColor = Color.parseColor("#33A1CC")
                    itemRowBinding.findViewById<ImageView>(R.id.imgActiveDot).visibility =
                        View.VISIBLE

                }
                else -> {
                    imgView.borderColor = Color.parseColor("#CC5E33")
                }
            }

        }
    }

    /**
     * Binding row item's data to the  view of the Item
     *
     * @author Yagna Joshi
     * @param holder
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position < 3)
            holder.bind(list[position], indexSubs)

    }

    /**
     * Updates the viewtype according to the intem postion
     *
     * @author Yagna Joshi
     * @param position
     */
    override fun getItemViewType(position: Int): Int {
        return if (position > 2) 1 else 0
    }


}
