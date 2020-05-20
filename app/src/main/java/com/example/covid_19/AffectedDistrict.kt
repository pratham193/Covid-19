package com.example.covid_19

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
import kotlinx.android.synthetic.main.activity_affected_district.*
import kotlinx.android.synthetic.main.activity_main.*
import org.eazegraph.lib.models.PieModel
import org.json.JSONArray
import org.json.JSONException
import kotlin.properties.Delegates

class AffectedDistrict : AppCompatActivity() {
    var districtModelsList: ArrayList<DistrictModel> = ArrayList()
   var pos by Delegates.notNull<Int>()
    var pie=ArrayList<Int>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_affected_district)
         pos=intent.getIntExtra("position",0)
        pie.add(0)
        pie.add(0)
        pie.add(0)
        pie.add(0)


        fetchData()
        supportActionBar?.title = "detailed State"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)




    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.getItemId() === android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }

    private fun fetchData() {
        val url = "https://api.covid19india.org/v2/state_district_wise.json"
        val request: StringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> {
                try {


                    var jsonArray= JSONArray(it)


                        val jo1 = jsonArray.getJSONObject(pos+1)
                        val state: String = jo1.getString("state")
                     val jo2 =    jo1.getJSONArray("districtData")
                       for(j in 0..jo2.length()-1) {
//                           var activeSum=0
//                           var confirmSum=0
//                           var deathsum=0
//                           var recoverSum=0

                           val jo = jo2.getJSONObject(j)
                           val district = jo.getString("district")
                           val active = jo.getString("active")
                           val confirm = jo.getString("confirmed")
                           val death = jo.getString("deceased")
                           val recover = jo.getString("recovered")

                           supportActionBar?.title = "Detailed "+ state




                           var districtModel = DistrictModel(
                               state, district, active, confirm, death, recover
                           )
                           pie[3]+=active.toInt()
                           pie[0]+=confirm.toInt()
                           pie[2]+=death.toInt()
                           pie[1]+=recover.toInt()



                           districtModelsList.add(districtModel)

                           if(j==jo2.length()-1){
                               x1.text= "${x1.text}" + "("+pie[0]+")"
                               x2.text= "${x2.text}" + "("+pie[1]+")"
                               x3.text= "${x3.text}" + "("+pie[2]+")"
                               x4.text= "${x4.text}" + "("+pie[3]+")"


                           }


                       }





                    var myDistrictAdapter=MyDistrictAdapter(this,districtModelsList)

                    piechart1.addPieSlice(
                        PieModel(
                            "Recoverd",
                            pie[1].toFloat(),
                            Color.parseColor("#66BB6A")
                        )
                    )
                    piechart1.addPieSlice(
                        PieModel(
                            "Deaths",
                            pie[2].toFloat(),
                            Color.parseColor("#EF5350")
                        )
                    )
                    piechart1.addPieSlice(
                        PieModel(
                            "Active",
                            pie[3].toFloat(),
                            Color.parseColor("#29B6F6")
                        )
                    )
                    if(pie[0].toInt()==0){
                        piechart1.addPieSlice(
                            PieModel(
                                "Cases",
                                pie[0].toFloat(),
                                Color.parseColor("#7b8788")
                            )
                        )
                    }else{
                        piechart1.addPieSlice(
                            PieModel(
                                "Cases",
                                pie[0].toFloat(),
                                Color.parseColor("#FFA726")
                            )
                        )
                    }
                    piechart1.startAnimation()

                    ll.adapter = myDistrictAdapter
                    al.stop()
                    al.visibility = View.GONE
                    xyz1.visibility = View.GONE



                } catch (e: JSONException) {
                    e.printStackTrace()





                }



            }, Response.ErrorListener {
                al.stop()
                al.visibility = View.GONE

                xyz1.visibility = View.GONE


                Toast.makeText(this,"Connection to Internet Required", Toast.LENGTH_SHORT).show()

            })
        var rq = Volley.newRequestQueue(this)
        rq.add(request)
    }
}
