package com.app.quickchat

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.quickchat.databinding.ActivityMainBinding
import com.app.quickchat.model.data.User
import com.app.quickchat.view.adapter.RecentsAdapter
import com.app.quickchat.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.google.i18n.phonenumbers.PhoneNumberUtil
import java.util.Locale

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    internal var activityMainBinding: ActivityMainBinding?= null
    var mainViewModel: MainViewModel? = null

    private var adapter: RecentsAdapter? = null

    private var map = getAllCountriesWithPhoneCodes().toSortedMap()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        //bind recylerview
        val recyclerView = activityMainBinding?.recentsRecyclerView
        recyclerView?.layoutManager = LinearLayoutManager(this)
        recyclerView?.setHasFixedSize(true)

        //init viewmodel
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        //init adapter
        adapter = RecentsAdapter()

        //set adapter
        recyclerView?.adapter = adapter

//        mainViewModel!!.list.value?.let { adapter!!.setRecentList(it) }

        mainViewModel?.list?.observe(this, Observer {
            adapter!!.setRecentList(it.reversed())
        })

        activityMainBinding?.openChatButton?.setOnClickListener {
            onClick()
        }
        activityMainBinding?.dropdownMenu?.setOnClickListener {
            showPopup(it)
        }
        activityMainBinding?.callButton?.setOnClickListener {
            call()
        }


    }

    private fun call() {
        val countryCode = activityMainBinding?.countryCode?.text.toString()
        val phoneNumber = activityMainBinding?.phone?.text.toString()

        val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", countryCode+phoneNumber, null))
        startActivity(intent)

        mainViewModel?.addUser(User(null,phoneNumber.toLong(),countryCode))
    }

    private fun showPopup(view: View) {
        val popup = PopupMenu(this,view)

        map.forEach { countryName, countryCode->
            popup.menu.add(0,countryCode.toInt(),0,"$countryName -> +$countryCode")
        }
        popup.show()

        popup.setOnMenuItemClickListener {
            activityMainBinding!!.countryCode.setText(it.itemId.toString())
            true
        }
    }


    private fun onClick() {
        val countryCode = activityMainBinding?.countryCode?.text.toString()
        val phoneNumber = activityMainBinding?.phone?.text.toString()


        val uri = Uri.parse("http://api.whatsapp.com/send?phone=+$countryCode$phoneNumber")
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setData(uri)
        startActivity(intent)

        mainViewModel?.addUser(User(null,phoneNumber.toLong(),countryCode))
    }

    private fun getAllCountriesWithPhoneCodes(): Map<String, String> {
        val phoneUtil = PhoneNumberUtil.getInstance()
        val countries = mutableMapOf<String, String>()
        for (regionCode in phoneUtil.supportedRegions) {
            val countryCode= phoneUtil.getCountryCodeForRegion(regionCode)
            val countryName = Locale("",regionCode).displayCountry

            countries[countryName] = countryCode.toString()
        }
        return countries
    }

}