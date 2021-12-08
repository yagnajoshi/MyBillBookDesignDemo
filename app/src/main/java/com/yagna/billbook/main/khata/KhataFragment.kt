package com.yagna.billbook.main.orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.yagna.billbook.R
import com.yagna.billbook.databinding.FragmentKhataBinding
import com.yagna.billbook.main.HomeActivity


class KhataFragment : Fragment() {
    private lateinit var toolbar: Toolbar
    private var khataViewModel: KhataViewModel? = null
    private lateinit var activity: HomeActivity
    private lateinit var binding: FragmentKhataBinding
    val PERMISSION_REQUEST = 1

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentKhataBinding.inflate(inflater, container, false)

        khataViewModel = ViewModelProvider(this).get(KhataViewModel::class.java)
        khataViewModel = ViewModelProvider(this).get(KhataViewModel::class.java)
        khataViewModel?.text?.observe(viewLifecycleOwner, { s -> })
        activity = (getActivity() as HomeActivity?)!!
        activity.changeAppbarVisibility(false)
        binding.toolbar.title = resources.getString(R.string.title_khata)
        return binding.root
    }






}