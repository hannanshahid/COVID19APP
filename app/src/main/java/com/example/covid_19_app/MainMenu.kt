package com.example.covid_19_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.covid_19_app.R.layout.activity_main_menu
import com.example.covid_19_app.Retrofit.RetrofitServiceForWorlddata
import com.example.covid_19_app.common.CommonForWorlddata
import kotlinx.android.synthetic.main.activity_main_menu.*


class MainMenu : AppCompatActivity() {
    lateinit var mServices:RetrofitServiceForWorlddata
    val homefrag=home()
    val pakfrag=Pakistan_fragment()
    val countriesfrag=countries_fragment()
    val infofrag=info_fragment()
    lateinit var active:Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main_menu)

        mServices = CommonForWorlddata.getcasesdata()
        val fm = getSupportFragmentManager()
        fm.beginTransaction().add(R.id.framelayout, infofrag, "4").commit()
        fm.beginTransaction().hide(infofrag).commit()
        fm.beginTransaction().add(R.id.framelayout, countriesfrag, "3").commit()
        fm.beginTransaction().hide(countriesfrag).commit()
        fm.beginTransaction().add(R.id.framelayout, pakfrag, "2").commit()
        fm.beginTransaction().hide(pakfrag).commit()
        fm.beginTransaction().add(R.id.framelayout, homefrag, "1").commit()

        active=homefrag


        pakbutton.setOnClickListener {
            val fm = getSupportFragmentManager()
                fm.beginTransaction().hide(active).show(pakfrag).commit()



            active = pakfrag


        }
        homebutton.setOnClickListener {
            val fm = getSupportFragmentManager()
                fm.beginTransaction().hide(active).show(homefrag).commit()



            active = homefrag

        }
        countriesbutton.setOnClickListener {
            val fm = getSupportFragmentManager()
                fm.beginTransaction().hide(active).show(countriesfrag).commit()



            active = countriesfrag
        }
        infobutton.setOnClickListener {

            val fm = getSupportFragmentManager()
                fm.beginTransaction().hide(active).show(infofrag).commit()



            active = infofrag

        }


    }
}