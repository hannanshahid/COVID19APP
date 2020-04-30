package com.example.covid_19_app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.covid_19_app.Models.Pakistanidata
import com.example.covid_19_app.Retrofit.RetrofitServiceForpak
import com.example.covid_19_app.common.CommonFroPakdata
import kotlinx.android.synthetic.main.fragment_pakistan_fragment.*
import kotlinx.android.synthetic.main.fragment_pakistan_fragment.view.*
import retrofit2.Call
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Pakistan_fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Pakistan_fragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    lateinit var mServices: RetrofitServiceForpak
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v=inflater.inflate(R.layout.fragment_pakistan_fragment, container, false)
        mServices= CommonFroPakdata.getpakdatasdata()
        getdataandshow(v)
        v.lastupdtae.setOnTouchListener(OnTouchListener { v, event ->

            if (event.action == MotionEvent.ACTION_UP) {
                if(event.getRawX() >= lastupdtae.getRight() - lastupdtae.getTotalPaddingRight()) {
                    getdataandshow(v)
                    // your action for drawable click event
                    return@OnTouchListener true
                }
            }
            true
        })
        return v
    }
    private fun getdataandshow(v: View?) {
        val sdf = SimpleDateFormat("dd MMM-yyyy hh:mm:ss ")
        val currentDate = sdf.format(Date())
        val url="https://corona.lmao.ninja/v2/countries/Pakistan/"
        mServices.getpakdataService(url).enqueue(object :retrofit2.Callback<Pakistanidata>{
            override fun onFailure(call: Call<Pakistanidata>, t: Throwable) {
                Toast.makeText(context,"No Internet Failed To upload Data",Toast.LENGTH_LONG).show()

            }

            override fun onResponse(call: Call<Pakistanidata>, response: Response<Pakistanidata>) {
                if(response!!.isSuccessful)
                {
                    //Toast.makeText(context,"Loading Done",Toast.LENGTH_LONG).show()
                   textViewtotalcasevalue.text=response!!.body()!!.cases.toString()
                    textViewtotalrecovercasevalue.text=response!!.body()!!.recovered.toString()
                    textViewtotalactivecasevalue.text=response!!.body()!!.active.toString()
                   textViewtotalDeathscasevalue.text=response!!.body()!!.deaths.toString()
                    textViewtotalcriticalcasevalue.text=response!!.body()!!.critical.toString()
                    textViewtotaltodaydeathcasevalue.text=response!!.body()!!.todayDeaths.toString()
                    textViewtotaltodaycasevalue.text=response!!.body()!!.todayCases.toString()
                    lastupdtae.text="Last Update: $currentDate"
                }
            }

        })

    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Pakistan_fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Pakistan_fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
