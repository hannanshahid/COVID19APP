package com.example.covid_19_app

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.covid_19_app.Adapters.allcountriesadapter
import com.example.covid_19_app.Models.Pakistanidata
import com.example.covid_19_app.Models.allcountry
import com.example.covid_19_app.Retrofit.RetrofitServiceForpak
import com.example.covid_19_app.Retrofit.RetrofitServicesforAllcountries
import com.example.covid_19_app.common.CommonFroPakdata
import com.example.covid_19_app.common.Commonforallcountries
import kotlinx.android.synthetic.main.fragment_countries_fragment.view.*
import kotlinx.android.synthetic.main.fragment_countries_fragment.view.countryspinner
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [countries_fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class countries_fragment : Fragment() {
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
    lateinit var mServices: RetrofitServicesforAllcountries
    lateinit var mServicesforbycountry: RetrofitServiceForpak

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v= inflater.inflate(R.layout.fragment_countries_fragment, container, false)
        val  lm= LinearLayoutManager(context)
        lm.orientation = RecyclerView.VERTICAL
        v.countriesrecyclerview.layoutManager=lm
        fillspinner(v)
        mServices=Commonforallcountries.getpakdatasdata()
        mServicesforbycountry=CommonFroPakdata.getpakdatasdata()
        getdataandshow(v)

       v.countryspinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                var  country = arrayOf<String>("All Countries","Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra", "Angola", "Anguilla",
                    "Antarctica", "Antigua and Barbuda", "Argentina", "Armenia", "Aruba", "Australia", "Austria",
                    "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium",
                    "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia and Herzegovina", "Botswana",
                    "Brazil", "British Indian Ocean Territory", "British Virgin Islands", "Brunei", "Bulgaria",
                    "Burkina Faso", "Burma (Myanmar)", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde",
                    "Cayman Islands", "Central African Republic", "Chad", "Chile", "China", "Christmas Island",
                    "Cocos (Keeling) Islands", "Colombia", "Comoros", "Cook Islands", "Costa Rica",
                    "Croatia", "Cuba", "Cyprus", "Czech Republic", "Democratic Republic of the Congo",
                    "Denmark", "Djibouti", "Dominica", "Dominican Republic",
                    "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia",
                    "Ethiopia", "Falkland Islands", "Faroe Islands", "Fiji", "Finland", "France", "French Polynesia",
                    "Gabon", "Gambia", "Gaza Strip", "Georgia", "Germany", "Ghana", "Gibraltar", "Greece",
                    "Greenland", "Grenada", "Guam", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana",
                    "Haiti", "Holy See (Vatican City)", "Honduras", "Hong Kong", "Hungary", "Iceland", "India",
                    "Indonesia", "Iran", "Iraq", "Ireland", "Isle of Man", "Israel", "Italy", "Ivory Coast", "Jamaica",
                    "Japan", "Jersey", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Kosovo", "Kuwait",
                    "Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein",
                    "Lithuania", "Luxembourg", "Macau", "Macedonia", "Madagascar", "Malawi", "Malaysia",
                    "Maldives", "Mali", "Malta", "Marshall Islands", "Mauritania", "Mauritius", "Mayotte", "Mexico",
                    "Micronesia", "Moldova", "Monaco", "Mongolia", "Montenegro", "Montserrat", "Morocco",
                    "Mozambique", "Namibia", "Nauru", "Nepal", "Netherlands", "Netherlands Antilles", "New Caledonia",
                    "New Zealand", "Nicaragua", "Niger", "Nigeria", "Niue", "Norfolk Island", "North Korea",
                    "Northern Mariana Islands", "Norway", "Oman", "Pakistan", "Palau", "Panama",
                    "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Pitcairn Islands", "Poland",
                    "Portugal", "Puerto Rico", "Qatar", "Republic of the Congo", "Romania", "Russia", "Rwanda",
                    "Saint Barthelemy", "Saint Helena", "Saint Kitts and Nevis", "Saint Lucia", "Saint Martin",
                    "Saint Pierre and Miquelon", "Saint Vincent and the Grenadines", "Samoa", "San Marino",
                    "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Serbia", "Seychelles", "Sierra Leone",
                    "Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Korea",
                    "Spain", "Sri Lanka", "Sudan", "Suriname", "Swaziland", "Sweden", "Switzerland",
                    "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand", "Timor-Leste", "Togo", "Tokelau",
                    "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Turks and Caicos Islands",
                    "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States", "Uruguay", "US Virgin Islands", "Uzbekistan", "Vanuatu", "Venezuela", "Vietnam",
                    "Wallis and Futuna", "West Bank", "Yemen", "Zambia", "Zimbabwe")
                var name=country[position]
                if(name!="All Countries") {

                    getcounrtydata(v, name)
                }
                else
                {
                    getdataandshow(v)
                }
            }

        }
        return v
    }

    private fun fillspinner(v:View) {
      var  country = arrayOf<String>("All Countries","Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra", "Angola", "Anguilla",
            "Antarctica", "Antigua and Barbuda", "Argentina", "Armenia", "Aruba", "Australia", "Austria",
            "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium",
            "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia and Herzegovina", "Botswana",
            "Brazil", "British Indian Ocean Territory", "British Virgin Islands", "Brunei", "Bulgaria",
            "Burkina Faso", "Burma (Myanmar)", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde",
            "Cayman Islands", "Central African Republic", "Chad", "Chile", "China", "Christmas Island",
            "Cocos (Keeling) Islands", "Colombia", "Comoros", "Cook Islands", "Costa Rica",
            "Croatia", "Cuba", "Cyprus", "Czech Republic", "Democratic Republic of the Congo",
            "Denmark", "Djibouti", "Dominica", "Dominican Republic",
            "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia",
            "Ethiopia", "Falkland Islands", "Faroe Islands", "Fiji", "Finland", "France", "French Polynesia",
            "Gabon", "Gambia", "Gaza Strip", "Georgia", "Germany", "Ghana", "Gibraltar", "Greece",
            "Greenland", "Grenada", "Guam", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana",
            "Haiti", "Holy See (Vatican City)", "Honduras", "Hong Kong", "Hungary", "Iceland", "India",
            "Indonesia", "Iran", "Iraq", "Ireland", "Isle of Man", "Israel", "Italy", "Ivory Coast", "Jamaica",
            "Japan", "Jersey", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Kosovo", "Kuwait",
            "Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein",
            "Lithuania", "Luxembourg", "Macau", "Macedonia", "Madagascar", "Malawi", "Malaysia",
            "Maldives", "Mali", "Malta", "Marshall Islands", "Mauritania", "Mauritius", "Mayotte", "Mexico",
            "Micronesia", "Moldova", "Monaco", "Mongolia", "Montenegro", "Montserrat", "Morocco",
            "Mozambique", "Namibia", "Nauru", "Nepal", "Netherlands", "Netherlands Antilles", "New Caledonia",
            "New Zealand", "Nicaragua", "Niger", "Nigeria", "Niue", "Norfolk Island", "North Korea",
            "Northern Mariana Islands", "Norway", "Oman", "Pakistan", "Palau", "Panama",
            "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Pitcairn Islands", "Poland",
            "Portugal", "Puerto Rico", "Qatar", "Republic of the Congo", "Romania", "Russia", "Rwanda",
            "Saint Barthelemy", "Saint Helena", "Saint Kitts and Nevis", "Saint Lucia", "Saint Martin",
            "Saint Pierre and Miquelon", "Saint Vincent and the Grenadines", "Samoa", "San Marino",
            "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Serbia", "Seychelles", "Sierra Leone",
            "Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Korea",
            "Spain", "Sri Lanka", "Sudan", "Suriname", "Swaziland", "Sweden", "Switzerland",
            "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand", "Timor-Leste", "Togo", "Tokelau",
            "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Turks and Caicos Islands",
            "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States", "Uruguay", "US Virgin Islands", "Uzbekistan", "Vanuatu", "Venezuela", "Vietnam",
            "Wallis and Futuna", "West Bank", "Yemen", "Zambia", "Zimbabwe")
        val a=ArrayAdapter(context!!,android.R.layout.simple_spinner_item,country)
        a.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        v.countryspinner.adapter=a
    }
    private fun getdataandshow(v: View?) {
        val sdf = SimpleDateFormat("dd MMM-yyyy hh:mm:ss ")
        val currentDate = sdf.format(Date())
        val url="https://corona.lmao.ninja/v2/countries/"
        mServices.getallcountryService(url).enqueue(object : Callback<List<allcountry>> {
            override fun onFailure(call: Call<List<allcountry>>, t: Throwable) {
              Log.e("error","$t")
            }

            override fun onResponse(
                call: Call<List<allcountry>>,
                response: Response<List<allcountry>>
            ) {
               val a=allcountriesadapter(context!!,response.body()!!)
                v!!.countriesrecyclerview.adapter=a
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
         * @return A new instance of fragment countries_fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            countries_fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


    private fun getcounrtydata(v:View?,name:String) {
        val url="https://corona.lmao.ninja/v2/countries/$name/"
        mServicesforbycountry.getpakdataService(url).enqueue(object : Callback<Pakistanidata> {
            override fun onFailure(call: Call<Pakistanidata>, t: Throwable) {
                Log.e("error","$t")
            }

            override fun onResponse(
                call: Call<Pakistanidata>,
                response: Response<Pakistanidata>
            ) {
                if(response!!.isSuccessful) {
                    var list: MutableList<allcountry> = java.util.ArrayList()
                    var ob = allcountry(
                        response!!.body()!!.active.toLong(),
                        response!!.body()!!.country,
                        response!!.body()!!.countryInfo,
                        response!!.body()!!.cases,
                        response!!.body()!!.todayCases,
                        response!!.body()!!.deaths,
                        response!!.body()!!.todayDeaths,
                        response!!.body()!!.recovered,
                        response!!.body()!!.active,
                        response!!.body()!!.critical,
                        response!!.body()!!.casesPerOneMillion,
                        response!!.body()!!.deathsPerOneMillion,
                        response!!.body()!!.tests,
                        response!!.body()!!.testsPerOneMillion,
                        response!!.body()!!.continent
                    )
                    list.add(ob)
                    val a = allcountriesadapter(context!!, list)
                    v?.countriesrecyclerview?.adapter = a

                }
                else
                {
                    Toast.makeText(context!!,"No Data Found Against $name Country",Toast.LENGTH_LONG).show()
                    getdataandshow(v)
                }

            }


        })
    }
}
