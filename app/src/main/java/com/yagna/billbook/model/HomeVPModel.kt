package com.yagna.billbook.model

/**
 * This is the Data Model Class for Home Viewpager
 *
 * @author Yagna
 */
class HomeVPModel(var activeOrders: ArrayList<Int> = ArrayList(),
                  var pendingOrders: ArrayList<Int> = ArrayList(),
                  var deliveries: ArrayList<Int> = ArrayList(),
                  var subscriptions: Int = 0,
                  var pendingDeliveries: Int = 0,
                  var newCustomers : ArrayList<Int> = ArrayList(),
                  var growth : Float = 0f,
                  var activeCustomers:  ArrayList<Int> = ArrayList(), )