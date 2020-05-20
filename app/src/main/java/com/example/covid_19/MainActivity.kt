package com.example.covid_19

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_affected__countries.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.xyz
import org.eazegraph.lib.models.PieModel
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
      

        fetchData()
        btncontry.setOnClickListener() {
            startActivity(Intent(this,AffectedCountries::class.java))
        }
        btnstates.setOnClickListener() {
            startActivity(Intent(this,AffectedStates::class.java))
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var inflater=menuInflater
        inflater.inflate(R.menu.example_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
     when(item.itemId){
             R.id.about->
                 startActivity(Intent(this,AboutUs::class.java))
             R.id.Authentication-> startActivity(Intent(this,Authentication::class.java))
     }
        return super.onOptionsItemSelected(item)
    }

    private fun fetchData() {
        val url = "https://corona.lmao.ninja/v2/all/"
        val request: StringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> {
                try {
                    val jo = JSONObject(it.toString())
                    tvCases.text = jo.getString("cases")
                    tvRecovered.text = jo.getString("recovered")
                    tvCritical.text = jo.getString("critical")
                    tvActive.text = jo.getString("active")
                    tvTodayCases.text = jo.getString("todayCases")
                    tvTotalDeaths.text = jo.getString("deaths")
                    tvTodayDeaths.text = jo.getString("todayDeaths")
                    tvAffectedCountries.text = jo.getString("affectedCountries")

                    piechart.addPieSlice(
                        PieModel(
                            "Cases",
                            tvCases.text.toString().toInt().toFloat(),
                            Color.parseColor("#FFA726")
                        )
                    )
                    piechart.addPieSlice(
                        PieModel(
                            "Recoverd",
                            tvRecovered.text.toString().toInt().toFloat(),
                            Color.parseColor("#66BB6A")
                        )
                    )
                    piechart.addPieSlice(
                        PieModel(
                            "Deaths",
                            tvTotalDeaths.text.toString().toInt().toFloat(),
                            Color.parseColor("#EF5350")
                        )
                    )
                    piechart.addPieSlice(
                        PieModel(
                            "Active",
                            tvActive.text.toString().toInt().toFloat(),
                            Color.parseColor("#29B6F6")
                        )
                    )
                    piechart.startAnimation()


                    simpleArcLoader.stop()
                    xyz.visibility=View.GONE
                    simpleArcLoader.visibility = View.GONE
                    scrollView.visibility = View.VISIBLE


                } catch (e: JSONException) {
                    e.printStackTrace()

                }


            }, Response.ErrorListener {
                simpleArcLoader.stop()
                xyz.visibility = View.GONE
                simpleArcLoader.visibility = View.GONE
                scrollView.visibility = View.VISIBLE

                Toast.makeText(this,"Connection to Internet Required", Toast.LENGTH_SHORT).show()

            })
        var rq = Volley.newRequestQueue(this)
        rq.add(request)
    }




}
