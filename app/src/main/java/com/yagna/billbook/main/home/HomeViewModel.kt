package com.yagna.billbook.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yagna.billbook.R
import com.yagna.billbook.model.NewOrderDataModel
import com.yagna.billbook.model.HomeVPModel
import java.util.*

class HomeViewModel : ViewModel() {
    private val mVPData: MutableLiveData<HomeVPModel> = MutableLiveData()
    val vPData: LiveData<HomeVPModel>
        get() = mVPData

    private val mItems: MutableLiveData<ArrayList<NewOrderDataModel>> =
        MutableLiveData<ArrayList<NewOrderDataModel>>()
    val items: LiveData<ArrayList<NewOrderDataModel>>
        get() = mItems


    private fun setDummyData() {
        val list: ArrayList<NewOrderDataModel> = getArrayList()
        val itemsViewDataModels: ArrayList<NewOrderDataModel> = list
        if (mItems.value?.size == null)
            mItems.value = itemsViewDataModels
        else
            mItems.value?.addAll(itemsViewDataModels)

    }

    private fun getArrayList(): ArrayList<NewOrderDataModel> {
        val newOrderArray: ArrayList<NewOrderDataModel> = ArrayList()
        newOrderArray.add(NewOrderDataModel("1", "09:00 AM"))
       
        return newOrderArray
    }

    fun init() {
        mVPData.value = getViewPagerData()
        setDummyData()
    }


    private fun getViewPagerData(): HomeVPModel {
        val vpData = HomeVPModel()
        vpData.activeOrders = arrayListOf(R.mipmap.prof_1, R.mipmap.prof_2, R.mipmap.prof_3)
        vpData.pendingOrders = arrayListOf(R.mipmap.prof_4, R.mipmap.prof_5)
        vpData.deliveries = arrayListOf(R.mipmap.prof_1, R.mipmap.prof_2, R.mipmap.prof_3)
        vpData.subscriptions = 10
        vpData.pendingDeliveries = 119
        vpData.newCustomers = arrayListOf(
            R.mipmap.prof_1, R.mipmap.prof_2, R.mipmap.prof_3,
            R.mipmap.prof_1, R.mipmap.prof_2, R.mipmap.prof_3,
            R.mipmap.prof_1, R.mipmap.prof_2, R.mipmap.prof_3,
            R.mipmap.prof_1, R.mipmap.prof_2, R.mipmap.prof_3,
            R.mipmap.prof_1, R.mipmap.prof_2, R.mipmap.prof_3
        )
        vpData.growth = 1.8f
        vpData.activeCustomers = arrayListOf(R.mipmap.prof_1, R.mipmap.prof_2, R.mipmap.prof_3)

        return vpData
    }
}
