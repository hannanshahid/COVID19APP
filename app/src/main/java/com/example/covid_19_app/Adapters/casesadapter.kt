package com.example.covid_19_app.Adapters

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.covid_19_app.R

import com.example.covid_19_app.dataclasses.WorldcaseDataclass
import kotlinx.android.synthetic.main.cardview.view.*

class casesadapter(val context: Context, val list: List<WorldcaseDataclass>): RecyclerView.Adapter<casesadapter.myviewholder>() {
    inner class myviewholder(itemView: View):RecyclerView.ViewHolder(itemView)
    {
        fun setdqata(h:WorldcaseDataclass) {
         itemView.textViewHader.text=h.Header
            itemView.textViewcasevalue.text=" ${h.value}"
            Log.i("c","${h.colour}")
            itemView.background.setTint(Color.parseColor("#${h.colour}"))


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myviewholder {
        val view= LayoutInflater.from(context).inflate(R.layout.cardview,parent,false)
        return myviewholder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: myviewholder, position: Int) {
        val h=list[position]
        holder.setdqata(h)
    }


}