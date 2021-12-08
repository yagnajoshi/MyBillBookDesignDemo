package com.yagna.billbook.main.customers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.yagna.billbook.R
import com.yagna.billbook.databinding.FragmentCustomersBinding
import com.yagna.billbook.main.HomeActivity


class CustomersFragment : Fragment() {

    private lateinit var viewModel: CustomersViewModel
    private lateinit var activity: HomeActivity
    private lateinit var binding: FragmentCustomersBinding

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentCustomersBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this).get(CustomersViewModel::class.java)
        viewModel.text.observe(viewLifecycleOwner, { s -> })
        activity = (getActivity() as HomeActivity?)!!
        activity.changeAppbarVisibility(false)
        binding.toolbarCustomers.title = resources.getString(R.string.title_customers)

        return binding.root
    }








}