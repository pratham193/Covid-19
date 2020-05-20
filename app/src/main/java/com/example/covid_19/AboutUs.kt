package com.example.covid_19

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_about_us.*
import kotlinx.android.synthetic.main.activity_authentication.*


class AboutUs : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)

        button3.setOnClickListener{
            val intent = Intent()
            intent.action = Intent.ACTION_VIEW
            intent.addCategory(Intent.CATEGORY_BROWSABLE)
            intent.data = Uri.parse("https://www.facebook.com/prathamesh.gj")
            startActivity(intent)
        }

        button4.setOnClickListener{
            val intent = Intent()
            intent.action = Intent.ACTION_VIEW
            intent.addCategory(Intent.CATEGORY_BROWSABLE)
            intent.data = Uri.parse("https://instagram.com/tech_lover_prathm?igshid=1p5vhhhoqmpt2")
            startActivity(intent)
        }
        button5.setOnClickListener{
            val intent = Intent()
            intent.action = Intent.ACTION_VIEW
            intent.addCategory(Intent.CATEGORY_BROWSABLE)
            intent.data = Uri.parse("https://github.com/pratham193")
            startActivity(intent)
        }
        button6.setOnClickListener{
            val intent = Intent()
            intent.action = Intent.ACTION_VIEW
            intent.addCategory(Intent.CATEGORY_BROWSABLE)
            intent.data = Uri.parse("https://instagram.com/tech_lover_prathm?igshid=1p5vhhhoqmpt2")
            startActivity(intent)
        }
        supportActionBar?.title = "ABOUT US"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.getItemId() === android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }





    }
