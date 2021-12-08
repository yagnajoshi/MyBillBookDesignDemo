package com.yagna.billbook.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.text.SpannableStringBuilder
import android.text.style.RelativeSizeSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.bold
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.google.android.flexbox.*
import com.yagna.billbook.databinding.ItemCard1Binding
import com.yagna.billbook.databinding.ItemCard2Binding
import com.yagna.billbook.databinding.ItemCard3Binding
import com.yagna.billbook.main.HomeActivity
import com.yagna.billbook.model.HomeVPModel
import com.yagna.billbook.utils.OverlapDecoration
import java.text.DecimalFormat
import java.text.NumberFormat

/**
 * This Adapter class is used to display Viewpager displayed on the Top of the HomeFragment
 *
 * @author Yagna Joshi
 */
class HomeViewPagerAdapter(
    private val data: HomeVPModel,
    private val context: HomeActivity,
) : PagerAdapter() {

    enum class PageType {
        INDEX_ORDERS,
        INDEX_SUBS,
        INDEX_CUSTOMERS,
        INDEX_ACITVE_CUSTOMERS
    }

    override fun getCount(): Int {
        return PageType.values().size-1
    }

    override fun isViewFromObject(
        view: View, `object`: Any
    ): Boolean {
        return view == `object`
    }

    /**
     * This method binds data according to pages
     *
     * @author Yagna Joshi
     */
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        when (position) {
            PageType.INDEX_SUBS.ordinal -> {
                val binding = getSubscriptionPage(container)
                container.addView(binding.root)
                return binding.root
            }
            PageType.INDEX_CUSTOMERS.ordinal -> {
                val binding = getCustomerPage(container)
                container.addView(binding.root)
                return binding.root
            }
            else -> {
                val binding = getOrdersPage(container)
                container.addView(binding.root)
                return binding.root
            }
        }

    }

    /**
     * This method returns ready to display view for OrdersPage
     *
     * @author Yagna Joshi
     * @param container
     */
    private fun getOrdersPage(container: ViewGroup): ItemCard1Binding {
        val binding = ItemCard1Binding.inflate(LayoutInflater.from(context), container, false)

        val f: NumberFormat = DecimalFormat("00")

        val vOrdersCount = f.format(data.activeOrders.size)
        val s = SpannableStringBuilder()
            .append("You have ")
            .bold { append(vOrdersCount) }
            .append(" active\n orders from")
        s.setSpan(RelativeSizeSpan(1.2f), 9, 9 + vOrdersCount.length, 0) // set size
        binding.txtActOrders.text = s

        val vPendingOrdersCount = f.format(data.pendingOrders.size)
        val str2 = SpannableStringBuilder()
            .bold { append(vPendingOrdersCount) }
            .append(" pending\n")
            .append("orders from")

        str2.setSpan(RelativeSizeSpan(1.2f), 0, vPendingOrdersCount.length, 0) // set size
        str2.setSpan(RelativeSizeSpan(0.8f), vPendingOrdersCount.length, vPendingOrdersCount.length+9, 0) // set size
        binding.txtPendingOrders.text = str2

        binding.rvActOrders.addItemDecoration(OverlapDecoration())
        binding.rvActOrders.layoutManager  = getFlexLayoutMgr()
        binding.rvActOrders.adapter = ImageStackListAdapter(data.activeOrders, PageType.INDEX_ORDERS)

        binding.rvPendingOrders.addItemDecoration(OverlapDecoration())
        binding.rvPendingOrders.layoutManager  = getFlexLayoutMgr()
        binding.rvPendingOrders.adapter = ImageStackListAdapter(data.pendingOrders, PageType.INDEX_ORDERS)

        return binding

    }


    /**
     * This method returns ready to display view for CustomerPage
     *
     * @author Yagna Joshi
     * @param container
     */
    @SuppressLint("SetTextI18n")
    private fun getCustomerPage(container: ViewGroup): ItemCard3Binding {

        val binding = ItemCard3Binding.inflate(LayoutInflater.from(context), container, false)

        val f: NumberFormat = DecimalFormat("00")

        val vNewCust = f.format(data.newCustomers.size)
        val s = SpannableStringBuilder()
            .bold { append(vNewCust) }
            .append(" New Customers")
        s.setSpan(RelativeSizeSpan(1.2f), 0, vNewCust.length, 0) // set size
        binding.txtActOrders.text = s


        val vActCustomers = f.format(data.activeCustomers.size)
        val str2 = SpannableStringBuilder()
            .bold { append(vActCustomers) }
            .append(" active\n")
            .append("Customers")

        str2.setSpan(
            RelativeSizeSpan(0.8f),
            vActCustomers.length + 1,
            vActCustomers.length + 1 + 7,
            0
        ) // set size
        str2.setSpan(RelativeSizeSpan(1.2f), 0, vActCustomers.length, 0) // set size
        binding.txtActiveCust.text = str2

        binding.rvNewCustomers.addItemDecoration(OverlapDecoration())
        binding.rvNewCustomers.layoutManager  = getFlexLayoutMgr()
        binding.rvNewCustomers.adapter = ImageStackListAdapter(data.newCustomers, PageType.INDEX_CUSTOMERS)

        binding.rvActCustomers.addItemDecoration(OverlapDecoration())
        binding.rvActCustomers.adapter = ImageStackListAdapter(data.activeCustomers, PageType.INDEX_ACITVE_CUSTOMERS)

        binding.txtGrowth.text = "${data.growth}%"
        if(data.growth>0)
        {
            //Positive chart
            binding.imgChart.setColorFilter(Color.argb(255, 49, 206, 140))
            binding.imgArrowUp.setColorFilter(Color.argb(255, 49, 206, 140))
        }
        else {

            //Negative chart
            binding.imgChart.setColorFilter(Color.argb(255, 244, 67, 54))
            binding.imgChart.scaleX = -1f

            binding.imgArrowUp.setColorFilter(Color.argb(255, 244, 67, 54))
            binding.imgArrowUp.rotation = 180f
        }

        return binding

    }


    /**
     * This method returns ready to display view for SubscriptionPage
     *
     * @author Yagna Joshi
     * @param container
     */
    private fun getSubscriptionPage(container: ViewGroup): ItemCard2Binding {
        val f: NumberFormat = DecimalFormat("00")
        val binding = ItemCard2Binding.inflate(LayoutInflater.from(context), container, false)
        val vDeliveries = f.format(data.deliveries.size)
        val s = SpannableStringBuilder()
            .bold { append(vDeliveries) }
            .append(" Deliveries")
        s.setSpan(RelativeSizeSpan(1.2f), 0, vDeliveries.length, 0) // set size
        binding.txtActOrders.text = s

        val vActiveSubs = f.format(data.subscriptions)
        val str2 = SpannableStringBuilder()
            .bold { append(vActiveSubs) }
            .append(" active\n")
            .append("Subscriptions")

        str2.setSpan(RelativeSizeSpan(1.2f), 0, vActiveSubs.length, 0) // set size
        str2.setSpan(
            RelativeSizeSpan(0.8f),
            vActiveSubs.length + 1,
            vActiveSubs.length + 1 + 7,
            0
        ) // set size

        binding.txtActiveSubs.text = str2

        val vPendingDeliveries = f.format(data.pendingDeliveries)

        val str3 = SpannableStringBuilder()
            .bold { append(vPendingDeliveries) }
            .append(" pending\n")
            .append("Deliveries")

        str3.setSpan(RelativeSizeSpan(1.2f), 0, vPendingDeliveries.length, 0) // set size
        str3.setSpan(
            RelativeSizeSpan(0.8f),
            vPendingDeliveries.length ,
            vPendingDeliveries.length + 9,
            0
        ) // set size
        binding.txtPendingSubs.text = str3

        binding.rvDeliveries.addItemDecoration(OverlapDecoration())
        binding.rvDeliveries.layoutManager  = getFlexLayoutMgr()
        binding.rvDeliveries.adapter = ImageStackListAdapter(data.deliveries,  PageType.INDEX_SUBS)

        return binding
    }

    /**
     * This method provides layout manager for child RecyclerView of the viewpager pages for displaying the
     * customer's avatar
     *
     * @author Yagna Joshi
     */
    private fun getFlexLayoutMgr(): RecyclerView.LayoutManager {
        val layoutManager  = FlexboxLayoutManager( context)
        layoutManager.justifyContent = JustifyContent.CENTER
        layoutManager.alignItems = AlignItems.CENTER
        layoutManager.flexDirection = FlexDirection. ROW
        layoutManager.flexWrap = FlexWrap.WRAP
        return layoutManager
    }

    override fun destroyItem(
        container: ViewGroup,
        position: Int,
        `object`: Any
    ) {
        container.removeView(`object` as View)
    }

}