package com.example.covid_19

import android.content.Context
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class MyDistrictAdapter(context: Context, districtModelsList: ArrayList<DistrictModel>) :
    ArrayAdapter<DistrictModel>(context,R.layout.list_district_coustom,districtModelsList) {
    var districtModelsListFiltered: ArrayList<DistrictModel> = districtModelsList


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var itemView: View?
        itemView = LayoutInflater.from(context).inflate(R.layout.list_district_coustom, null, true)

        val tvdistrict: TextView = itemView.findViewById(R.id.tvdistrict)
        val tvconfirmed: TextView = itemView.findViewById(R.id.tvconfirmedD)
        val tvdeaths: TextView = itemView.findViewById(R.id.tvdeathsD)
        val tvrecover: TextView = itemView.findViewById(R.id.tvrecoverD)
        val tvactive: TextView = itemView.findViewById(R.id.tvactiveD)


        val content = SpannableString(districtModelsListFiltered[position].district)
        content.setSpan(UnderlineSpan(), 0, content.length, 0)
        tvdistrict.text = content

        tvconfirmed.text = districtModelsListFiltered[position].confirmed
        tvdeaths.text = districtModelsListFiltered[position].deaths
        tvrecover.text = districtModelsListFiltered[position].recoverd
        tvactive.text = districtModelsListFiltered[position].active



        return itemView
    }
}
