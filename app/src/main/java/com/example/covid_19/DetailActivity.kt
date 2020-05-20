package com.example.covid_19

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_affected__countries.*
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_detail.chart
import kotlinx.android.synthetic.main.activity_detail.scrollView
import kotlinx.android.synthetic.main.activity_detail.simpleArcLoader
import kotlinx.android.synthetic.main.activity_detail.tvActive
import kotlinx.android.synthetic.main.activity_detail.tvCases
import kotlinx.android.synthetic.main.activity_detail.tvCritical
import kotlinx.android.synthetic.main.activity_detail.tvRecovered
import kotlinx.android.synthetic.main.activity_detail.tvTodayCases
import kotlinx.android.synthetic.main.activity_detail.tvTodayDeaths
import kotlinx.android.synthetic.main.activity_detail.tvTotalDeaths
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_coustom_item.*
import org.eazegraph.lib.models.PieModel
import org.json.JSONArray
import org.json.JSONException
import java.util.ArrayList

class DetailActivity : AppCompatActivity() {



    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        var positionCountry =intent.getIntExtra("position",0)


        
            val url = "https://corona.lmao.ninja/v2/countries/"
            val request: StringRequest = StringRequest(
                Request.Method.GET, url,
                Response.Listener<String> {
                    try {
                        var jsonArray=JSONArray(it)
                            val jo = jsonArray.getJSONObject(positionCountry)
                        val countryName = jo.getString("country")
                        val cases = jo.getString("cases")
                        val  todayCases = jo.getString("todayCases")
                        val   deaths = jo.getString("deaths")
                        val  todayDeaths = jo.getString("todayDeaths")
                        val  recovered = jo.getString("recovered")
                        val   active = jo.getString("active")
                        val   critical = jo.getString("critical")
                        val   tests = jo.getString("tests")

                        supportActionBar?.title = "Details of "+ countryName
                        supportActionBar?.setDisplayHomeAsUpEnabled(true)
                        supportActionBar?.setDisplayShowHomeEnabled(true)


                        tvCountry.text= countryName
                        tvCases.text=cases
                        tvTodayDeaths.text=todayDeaths
                        tvRecovered.text=recovered
                        tvCritical.text=critical
                        tvActive.text=active
                        tvTodayCases.text=todayCases
                        tvTotalDeaths.text=deaths
                        tvTests.text=tests


                        chart.addPieSlice(
                            PieModel(
                                "Recoverd",
                                recovered.toFloat(),
                                Color.parseColor("#66BB6A")
                            )
                        )
                        chart.addPieSlice(
                            PieModel(
                                "Deaths",
                                deaths.toFloat(),
                                Color.parseColor("#EF5350")
                            )
                        )
                        chart.addPieSlice(
                            PieModel(
                                "Active",
                                active.toFloat(),
                                Color.parseColor("#29B6F6")
                            )
                        )
                        if(cases.toInt()==0) {
                            chart.addPieSlice(
                                PieModel(
                                    "Cases",
                                    cases.toFloat(),
                                    Color.parseColor("#7b8788" +
                                            "")
                                )
                            )
                        } else{
                            chart.addPieSlice(
                                PieModel(
                                    "Cases",
                                    cases.toFloat(),
                                    Color.parseColor("#FFA726")))

                        }
                        chart.startAnimation()

                        simpleArcLoader.stop()
                        simpleArcLoader.visibility = View.GONE
                        scrollView.visibility = View.VISIBLE

                    }  catch (e: JSONException) {
                        e.printStackTrace()
                        Toast.makeText(this,"error", Toast.LENGTH_SHORT).show()
                    }
                }, Response.ErrorListener {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    simpleArcLoader.stop()
                    simpleArcLoader.visibility = View.GONE
                    scrollView.visibility = View.VISIBLE
                })
        var rq = Volley.newRequestQueue(this)
        rq.add(request)







    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.getItemId() === android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }
}
