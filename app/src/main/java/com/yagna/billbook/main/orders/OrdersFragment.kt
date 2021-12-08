package com.yagna.billbook.main.orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.yagna.billbook.R
import com.yagna.billbook.databinding.FragmentOrdersBinding
import com.yagna.billbook.main.HomeActivity


class OrdersFragment : Fragment() {
    private lateinit var binding: FragmentOrdersBinding
    private lateinit var root: View
    private lateinit var ordersViewModel: OrdersViewModel
    private var activity: HomeActivity? = null
    private lateinit var toolbar: Toolbar

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentOrdersBinding.inflate(inflater, container, false)

        ordersViewModel = ViewModelProvider(this).get(OrdersViewModel::class.java)
        activity = getActivity() as HomeActivity?
        activity?.changeAppbarVisibility(false)
        ordersViewModel.text.observe(viewLifecycleOwner, {})
        binding.toolbar.title = resources.getString(R.string.title_orders)

        return binding.root
    }




}