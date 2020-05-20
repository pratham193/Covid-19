package com.example.covid_19

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_affected__countries.*
import kotlinx.android.synthetic.main.activity_affected__countries.listView
import kotlinx.android.synthetic.main.activity_affected__countries.loader

import kotlinx.android.synthetic.main.affected_states.*
import kotlinx.android.synthetic.main.list_state_coustom.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class AffectedStates: AppCompatActivity() {

    var stateModelsList: ArrayList<StateModel> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.affected_states)

        supportActionBar?.title = "Affected States"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)


        fetchData()

        listView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
               var intent= Intent(
                    this,
                    AffectedDistrict::class.java
                )
                intent.putExtra("position", position)

                startActivity(intent)

            }
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


                    var jsonArray=JSONArray(it)

                    for(i in 0 .. jsonArray.length()-1){
                        val jo1 = jsonArray.getJSONObject(i)


                        val state: String = jo1.getString("state")
                        if(i!=0) {
                            var stateModel = StateModel(
                                state
                            )



                            stateModelsList.add(stateModel)
                        }
                    }
                    var myStateAdapter=MyStateAdapter(this,stateModelsList)
                    listView.adapter = myStateAdapter
                    loader.stop()
                    loader.visibility = View.GONE
                    xyz3.visibility=View.GONE


                } catch (e: JSONException) {
                    e.printStackTrace()




                }



            }, Response.ErrorListener {
                loader.stop()
                loader.visibility = View.GONE
                xyz3.visibility=View.GONE



                Toast.makeText(this,"Connection to Internet Required", Toast.LENGTH_SHORT).show()

            })
        var rq = Volley.newRequestQueue(this)
        rq.add(request)
    }
}
