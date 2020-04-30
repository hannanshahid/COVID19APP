package com.example.covid_19_app.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.covid_19_app.Models.allcountry
import com.example.covid_19_app.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cardview_for_allcountries.view.*

class allcountriesadapter(val context: Context, val list: List<allcountry>): RecyclerView.Adapter<allcountriesadapter.myviewholder>() {
    inner class myviewholder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        fun setdqata(h:allcountry)
        {
          itemView.textViewcountryname.text=h.country.toString()
            itemView.textViewctotalcasesvalue.text=h.cases.toString()
            itemView.textViewRecovers.text=h.recovered.toString()
            itemView.textViewDeaths.text=h.deaths.toString()
            itemView.textViewactive.text=h.active.toString()
            itemView.textViewcriticalcard.text=h.critical.toString()
            itemView.textViewTodaycase.text=h.todayCases.toString()
            itemView.textViewTodaydeaths.text=h.todayDeaths.toString()
            itemView.textViewTodaytest.text=h.tests.toString()
            Picasso.get().load(h.countryInfo.flag).into(itemView.flagimage)


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): allcountriesadapter.myviewholder {
        val view= LayoutInflater.from(context).inflate(R.layout.cardview_for_allcountries,parent,false)
        return myviewholder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: allcountriesadapter.myviewholder, position: Int) {
        val h=list[position]
        holder.setdqata(h)

    }
}