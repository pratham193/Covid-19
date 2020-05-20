package com.example.covid_19

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_affected__countries.*
import org.json.JSONArray
import org.json.JSONException


 class AffectedCountries : AppCompatActivity() {

   var countryModelsList: ArrayList<CountryModel> = ArrayList()




     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_affected__countries)


        supportActionBar?.title = "Affected Countries"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        fetchData()

        listView.onItemClickListener =
            OnItemClickListener { parent, view, position, id ->
                startActivity(
                    Intent(
                        this,
                        DetailActivity::class.java
                    ).putExtra("position", position)
                )
            }
     }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.getItemId() === android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }
    private fun fetchData() {
        val url = "https://corona.lmao.ninja/v2/countries/"
        val request: StringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> {
             try {
                 var jsonArray=JSONArray(it)
                 for(i in 0 .. jsonArray.length()-1){
                     val jo = jsonArray.getJSONObject(i)
                     val countryName: String = jo.getString("country")



                     val flagUrl = jo.getJSONObject("countryInfo").getString("flag")

                    var countryModel = CountryModel(
                         flagUrl,
                         countryName
                         )

                     countryModelsList.add(countryModel)
                 }
                 var myCustomAdapter=MyCustomAdapter(this,countryModelsList)
                 listView.adapter = myCustomAdapter
                 loader.stop()
                 loader.visibility = View.GONE
                 xyz2.visibility = View.GONE


             }  catch (e: JSONException) {
                 e.printStackTrace()






             }



            }, Response.ErrorListener {
                loader.stop()
                xyz2.visibility = View.GONE
                loader.visibility = View.GONE


                Toast.makeText(this,"Connection to Internet Required", Toast.LENGTH_SHORT).show()

            })
        var rq = Volley.newRequestQueue(this)
        rq.add(request)
    }




     }


