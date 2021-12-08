package com.yagna.billbook.main

import android.animation.LayoutTransition
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.yagna.billbook.R

import kotlinx.android.synthetic.main.activity_main.*

/**
 * This is HomeActivity. It has bottom navigation bar to navigate to the fragments
 *
 * @author Yagna Joshi
 */
class HomeActivity : AppCompatActivity() {


    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var toolbarView: Toolbar
    private lateinit var appBar: AppBarLayout
    private lateinit var navHostFragment: Fragment

    /**
     * This method will be called first on launch of this activity
     *
     * @author Yagna Joshi
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        toolbarView = toolbar
        appBar = app_bar
        navHostFragment = nav_host_fragment
        supportActionBar?.setDisplayShowTitleEnabled(false)
        bottomNavigation = bottomNavigationView as BottomNavigationView
        val appBarConfiguration = AppBarConfiguration.Builder(
            R.id.navigation_home,
            R.id.navigation_customers,
            R.id.navigation_khata,
            R.id.navigation_orders
        )
            .build()
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
        NavigationUI.setupWithNavController(bottomNavigation, navController)
        bottomNavigation.background = null
        bottomNavigation.menu.getItem(2).isEnabled = false

        txtBadgeNotification.text = "2"

        (findViewById<ViewGroup>(R.id.clRoot)).layoutTransition
            .enableTransitionType(LayoutTransition.CHANGING)

    }

    /**
     * This method handles the fab button's visibility
     *
     * @author Yagna Joshi
     * @param v
     */
    fun updateMenuPopupVisibility(v: View) {
        llFabPopup.visibility =
            if (llFabPopup.visibility == View.VISIBLE) View.GONE else View.VISIBLE
    }

    /**
     * This changeAppbarVisibility method handles the AppBar's visibility
     *
     * @author Yagna Joshi
     * @param isVisible
     */
    fun changeAppbarVisibility(isVisible: Boolean) {
        navHostFragment.view?.layoutParams as CoordinatorLayout.LayoutParams
        if (isVisible) {
            val lp = appBar.layoutParams as CoordinatorLayout.LayoutParams
            lp.height = CoordinatorLayout.LayoutParams.WRAP_CONTENT
            appBar.layoutParams = lp
        } else {
            val lp = appBar.layoutParams as CoordinatorLayout.LayoutParams
            lp.height = 0
            appBar.layoutParams = lp
        }
    }
    

}