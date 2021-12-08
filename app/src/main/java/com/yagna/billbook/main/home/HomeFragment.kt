package com.yagna.billbook.main.home

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.michalsvec.singlerowcalendar.calendar.CalendarChangesObserver
import com.michalsvec.singlerowcalendar.calendar.CalendarViewManager
import com.michalsvec.singlerowcalendar.calendar.SingleRowCalendar
import com.michalsvec.singlerowcalendar.calendar.SingleRowCalendarAdapter
import com.michalsvec.singlerowcalendar.selection.CalendarSelectionManager
import com.michalsvec.singlerowcalendar.utils.DateUtils
import com.yagna.billbook.R
import com.yagna.billbook.adapter.HomeViewPagerAdapter
import com.yagna.billbook.adapter.NewOrderRecycleAdapter
import com.yagna.billbook.databinding.FragmentHomeBinding
import com.yagna.billbook.model.NewOrderDataModel
import com.yagna.billbook.main.HomeActivity
import com.whiteelephant.monthpicker.MonthPickerDialog
import kotlinx.android.synthetic.main.calendar_item.view.*
import kotlinx.android.synthetic.main.layout_calenderview.view.*
import java.util.*


class HomeFragment : Fragment() {
    private lateinit var txtDateToday: String
    private lateinit var binding: FragmentHomeBinding
    private var homeViewModel: HomeViewModel? = null
    private lateinit var activity: HomeActivity
    lateinit var calenderLayout: LinearLayout
    private lateinit var calenderView: SingleRowCalendar
    private val calendar = Calendar.getInstance()
    private var currentMonth = 0
    private var currentYear = 0
    private var currDate = 0
    private lateinit var newOrderAdapter: NewOrderRecycleAdapter
    private var newOrderArray: ArrayList<NewOrderDataModel> = ArrayList()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        activity = (getActivity() as HomeActivity?)!!

        setRecyclerView()
        initViewModel()
        initCalanderView()
        setupCalender()

        return binding.root
    }

    private fun initViewModel() {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        homeViewModel!!.vPData.observe(viewLifecycleOwner, {
            val adapter = HomeViewPagerAdapter(it, activity)
            binding.viewPager.adapter = adapter
            binding.viewPager.setPadding(20, 0, 20, 0)
            binding.viewPager.clipToPadding = false
            binding.viewPager.pageMargin = -20
        })

        homeViewModel!!.items.observe(activity, {
            newOrderArray.clear()
            newOrderArray.addAll(it)
            newOrderAdapter.notifyDataSetChanged()
        })

        homeViewModel!!.init()
    }

    /*Function for displaying ListView*/
    private fun setRecyclerView() {
        newOrderAdapter = NewOrderRecycleAdapter(newOrderArray)
        binding.adpter = newOrderAdapter
    }

    private fun initCalanderView() {
        calendar.time = Date()
        currentMonth = calendar[Calendar.MONTH]
        currDate = (calendar[Calendar.DATE] - 1)
        currentYear = calendar[Calendar.YEAR]
        txtDateToday =
            "${DateUtils.getMonth3LettersName(calendar.time)}, ${DateUtils.getDayNumber(calendar.time)} ${DateUtils.getYear(calendar.time)}"

        binding.txtSDate.text = txtDateToday
        val initCurrMonthText =
            "${DateUtils.getMonth3LettersName(calendar.time)}, ${DateUtils.getYear(calendar.time)}"
        calenderLayout = binding.calendarLayout
        calenderView = calenderLayout.findViewById(R.id.main_single_row_calendar)
        binding.txtCurrMonth.text = initCurrMonthText

        binding.rlCurrMonth.setOnClickListener {
            openMonthYearPicker(activity)
        }
    }

    override fun onStart() {
        activity.changeAppbarVisibility(true)
        super.onStart()
    }


    private fun setupCalender() {
        val myCalendarViewManager = object : CalendarViewManager {
            override fun setCalendarViewResourceId(
                position: Int,
                date: Date,
                isSelected: Boolean
            ): Int {
                return if (isSelected)
                    R.layout.selected_calendar_item
                else
                    R.layout.calendar_item
            }

            override fun bindDataToCalendarView(
                holder: SingleRowCalendarAdapter.CalendarViewHolder,
                date: Date,
                position: Int,
                isSelected: Boolean
            ) {
                holder.itemView.tv_date_calendar_item.text = DateUtils.getDayNumber(date)
                holder.itemView.tv_day_calendar_item.text = DateUtils.getDay3LettersName(date)
            }
        }

        val mySelectionManager = object : CalendarSelectionManager {
            override fun canBeItemSelected(position: Int, date: Date): Boolean {
                return true
            }
        }

        val myCalendarChangesObserver = object : CalendarChangesObserver {
            override fun whenSelectionChanged(isSelected: Boolean, position: Int, date: Date) {
            calendar.time = date
                val textDateString =
                    "${DateUtils.getMonth3LettersName(date)}, ${DateUtils.getDayNumber(date)} ${DateUtils.getYear(date)}"

                binding.txtSDate.text = textDateString
                if (txtDateToday.trim() == textDateString.trim()) {
                    binding.txtToday.visibility = View.VISIBLE
                } else {
                    binding.txtToday.visibility = View.INVISIBLE
                }
                currDate = position

                super.whenSelectionChanged(isSelected, position, date)
            }
        }

        calenderLayout.main_single_row_calendar.apply {
            calendarViewManager = myCalendarViewManager
            calendarChangesObserver = myCalendarChangesObserver
            calendarSelectionManager = mySelectionManager
            includeCurrentDate = true
            setDates(getFutureDatesOfCurrentMonth())
            init()
            select(currDate)
            smoothScrollToPosition(currDate)

        }


    }


    private fun getFutureDatesOfCurrentMonth(): List<Date> {
        // get all next dates of current month
        currentMonth = calendar[Calendar.MONTH]
        return getDates(mutableListOf())
    }


    private fun getDates(list: MutableList<Date>): List<Date> {
        // load dates of whole month
        calendar.set(Calendar.MONTH, currentMonth)
        calendar.set(Calendar.YEAR, currentYear)
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        list.add(calendar.time)
        while (currentMonth == calendar[Calendar.MONTH]) {
            calendar.add(Calendar.DATE, +1)
            if (calendar[Calendar.MONTH] == currentMonth)
                list.add(calendar.time)
        }
        calendar.add(Calendar.DATE, -1)
        return list
    }


    private fun openMonthYearPicker(activity: Context?) {
        val builder = MonthPickerDialog.Builder(activity, { mon, yer ->
            this.currentMonth = mon
            this.currentYear = yer
            calendar.set(Calendar.MONTH, currentMonth)
            calendar.set(Calendar.YEAR, currentYear)
            setupCalender()
            val textDatString = "${DateUtils.getMonth3LettersName(calendar.time)},${DateUtils.getDayNumber(calendar.time)} ${DateUtils.getYear(calendar.time)}"
            binding.txtSDate.text = textDatString
            val textMothString = "${DateUtils.getMonth3LettersName(calendar.time)}, ${DateUtils.getYear(calendar.time)}"
            binding.txtCurrMonth.text = textMothString

        }, calendar[Calendar.YEAR], calendar[Calendar.MONTH])

        builder.setActivatedMonth(Calendar.JULY)
            .setMinYear(1990)
            .setActivatedYear(currentYear)
            .setActivatedMonth(currentMonth)
            .setMaxYear(2030)
            .setMinMonth(Calendar.JANUARY)
            .setTitle("")
            .setMonthRange(Calendar.JANUARY, Calendar.DECEMBER)
            .setOnMonthChangedListener {}
            .setOnYearChangedListener {}
            .build()
            .show()
    }



}