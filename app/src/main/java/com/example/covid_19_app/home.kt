package com.example.covid_19_app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.covid_19_app.Adapters.casesadapter
import com.example.covid_19_app.Models.casedata
import com.example.covid_19_app.Retrofit.RetrofitServiceForWorlddata
import com.example.covid_19_app.common.CommonForWorlddata
import com.example.covid_19_app.dataclasses.WorldcaseDataclass
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.fragment_home.view.progressBarinhome
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
 * Use the [home.newInstance] factory method to
 * create an instance of this fragment.
 */
class home : Fragment() {
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
    lateinit var mServices: RetrofitServiceForWorlddata
    lateinit var currentcase:casedata

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       val V= inflater.inflate(R.layout.fragment_home, container, false)
        V.progressBarinhome.visibility=View.INVISIBLE
        mServices=CommonForWorlddata.getcasesdata()



        val  lm= LinearLayoutManager(context)
        lm.orientation = RecyclerView.HORIZONTAL
        V.recyclerviewtotaltoday.layoutManager=lm
        val  lm2= LinearLayoutManager(context)
        lm2.orientation = RecyclerView.HORIZONTAL
        V.recyclerviewtotalworld.layoutManager=lm2
        V.recyclerviewtotaltoday.isNestedScrollingEnabled=false
        V.recyclerviewtotalworld.isNestedScrollingEnabled=false
        getdataandshow(V,1)

        V.lastupdtaehomescreen.setOnTouchListener(View.OnTouchListener { v, event ->

            if (event.action == MotionEvent.ACTION_UP) {
                if (event.getRawX() >= v.lastupdtaehomescreen.getRight() - v.lastupdtaehomescreen.getTotalPaddingRight()) {
                    V.progressBarinhome.visibility=View.INVISIBLE
                      getdataandshow(V,2)
                    // your action for drawable click event
                    return@OnTouchListener true
                }
            }
            true
        })
        return V

    }


    private fun getdataandshow(v: View?,s:Int) {

        if(s==1) {
            v!!.progressBarinhome.visibility = View.VISIBLE
        }
        var list : MutableList<WorldcaseDataclass> = ArrayList()
        var listtoday : MutableList<WorldcaseDataclass> = ArrayList()
        val sdf = SimpleDateFormat("dd MMM-yyyy hh:mm:ss ")
        val currentDate = sdf.format(Date())
        val url="https://corona.lmao.ninja/v2/all"
        mServices.getcasesdataService(url).enqueue(object :retrofit2.Callback<casedata>{
            override fun onFailure(call: Call<casedata>, t: Throwable) {
                Toast.makeText(context,"No Internet Failed To upload Data",Toast.LENGTH_LONG).show()
                if(s==1) {
                    v!!.progressBarinhome.visibility = View.INVISIBLE
                }

            }

            override fun onResponse(call: Call<casedata>, response: Response<casedata>) {
                currentcase=response!!.body()!!

                if(response!!.isSuccessful)
                {
                    //Toast.makeText(context,"Loading Done",Toast.LENGTH_LONG).show()
                    val tcase:WorldcaseDataclass=WorldcaseDataclass()
                    tcase.Header="Total Cases"
                    tcase.value=response!!.body()!!.cases.toString()

                    tcase.colour="3490dc"
                    list.add(tcase)
                    val tcaseRe:WorldcaseDataclass=WorldcaseDataclass()
                    tcaseRe.Header="Total Recoverd"
                    tcaseRe.value=response!!.body()!!.recovered.toString()

                    tcaseRe.colour="7ebf4d"
                    list.add(tcaseRe)
                    val tcasedeath:WorldcaseDataclass=WorldcaseDataclass()
                    tcasedeath.Header="Deaths"
                    tcasedeath.value=response!!.body()!!.deaths.toString()

                    tcasedeath.colour="fa3e45"
                    list.add(tcasedeath)
                    val tcaseCr:WorldcaseDataclass=WorldcaseDataclass()
                    tcaseCr.Header="Critical Cases"
                    tcaseCr.value=response!!.body()!!.critical.toString()

                    tcaseCr.colour="9c3efa"
                    list.add(tcaseCr)
                    val tcaseac:WorldcaseDataclass=WorldcaseDataclass()
                    tcaseac.Header="Active Cases"
                    tcaseac.value=response!!.body()!!.active.toString()

                    tcaseac.colour="fc6b03"
                    list.add(tcaseac)
                    val totalcountryeffect:WorldcaseDataclass=WorldcaseDataclass()
                    totalcountryeffect.Header="Countries Effected"
                    totalcountryeffect.value=response!!.body()!!.affectedCountries.toString()

                    totalcountryeffect.colour="faac3e"
                    list.add(totalcountryeffect)


                    val todaycase:WorldcaseDataclass=WorldcaseDataclass()
                    todaycase.Header="Total Cases"
                    todaycase.value=response!!.body()!!.todayCases.toString()

                    todaycase.colour="3490dc"
                    listtoday.add(todaycase)

                    val todaycasedeath:WorldcaseDataclass=WorldcaseDataclass()
                    todaycasedeath.Header="Deaths"
                    todaycasedeath.value=response!!.body()!!.todayDeaths.toString()

                    todaycasedeath.colour="fa3e45"
                    listtoday.add(todaycasedeath)

                    val todaycaseac:WorldcaseDataclass=WorldcaseDataclass()
                    todaycaseac.Header="Active Cases"
                    todaycaseac.value=response!!.body()!!.active.toString()

                    todaycaseac.colour="fc6b03"
                    listtoday.add(todaycaseac)


                    if(list!=null && listtoday!=null)
                    {
                        val a = casesadapter(context!!, list)
                        v!!.recyclerviewtotalworld.adapter = a
                        val todayaapadter = casesadapter(context!!, listtoday)
                        v!!.recyclerviewtotaltoday.adapter = todayaapadter
                            v.lastupdtaehomescreen.text="Last Update: $currentDate"
                        if(s==1) {
                            v.progressBarinhome.visibility = View.INVISIBLE
                        }

                    }


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
         * @return A new instance of fragment home.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            home().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
