package com.example.covid_19

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.Nullable
import com.bumptech.glide.Glide
import java.security.AlgorithmConstraints
import java.util.Locale.filter


class MyCustomAdapter(context: Context, countryModelsList: ArrayList<CountryModel>) :
    ArrayAdapter<CountryModel>(context,R.layout.list_coustom_item,countryModelsList ) {
    var countryModelsListFiltered: ArrayList<CountryModel> =countryModelsList




   // constructor(context: Context): this(context, emptyList<CountryModel>())


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var itemView:View?
        itemView=LayoutInflater.from(context).inflate(R.layout.list_coustom_item,null,true)
//        val itemData=getItem(position)
//        val imageView=itemView.findViewById<ImageView>(R.id.imageFlag)
//        val tvCountryName=itemView.findViewById<TextView>(R.id.tvCountryName)
//
//        tvCountryName.text=itemData?.country.toString()
//        Glide.with(context).load(itemData?.flag.toString()).into(imageView)
        val tvCountryName: TextView = itemView.findViewById(R.id.tvCountryName)
        val imageView: ImageView = itemView.findViewById(R.id.imageFlag)

        tvCountryName.text = countryModelsListFiltered[position].country
        Glide.with(context).load(countryModelsListFiltered[position].flag).into(imageView)



        return itemView
    }

    @Nullable
    override fun getItem(position: Int): CountryModel? {
        return countryModelsListFiltered[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return countryModelsListFiltered.size
    }

    override fun getFilter(): Filter {



        return super.getFilter()
    }

   

}