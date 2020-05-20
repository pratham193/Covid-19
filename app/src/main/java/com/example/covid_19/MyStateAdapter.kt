package com.example.covid_19

import android.content.Context
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView


class MyStateAdapter(context: Context, stateModelsList: ArrayList<StateModel>) :
    ArrayAdapter<StateModel>(context,R.layout.list_state_coustom,stateModelsList) {
    var stateModelsListFiltered: ArrayList<StateModel> =stateModelsList







    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var itemView: View?
        itemView= LayoutInflater.from(context).inflate(R.layout.list_state_coustom,null,true)

        val tvstate: TextView = itemView.findViewById(R.id.tvstate)
//        val tvconfirmed: TextView = itemView.findViewById(R.id.tvconfirmed)
//        val tvdeaths: TextView = itemView.findViewById(R.id.tvdeaths)
//        val tvrecover: TextView = itemView.findViewById(R.id.tvrecover)
//        val tvactive: TextView = itemView.findViewById(R.id.tvactive)

        val content = SpannableString(stateModelsListFiltered[position].state)
        content.setSpan(UnderlineSpan(), 0, content.length, 0)
        tvstate.text = content

//        tvconfirmed.text = stateModelsListFiltered[position].confirmed
//        tvdeaths.text = stateModelsListFiltered[position].deaths
//        tvrecover.text = stateModelsListFiltered[position].recoverd
//        tvactive.text= addthis( tvconfirmed.text.toString().toInt(),tvdeaths.text.toString().toInt(),tvrecover.text.toString().toInt())




        return itemView
    }

//    private fun addthis(x: Int, y: Int, z: Int): CharSequence? {
//        return x.minus(y+z).toString()
//    }

    }

