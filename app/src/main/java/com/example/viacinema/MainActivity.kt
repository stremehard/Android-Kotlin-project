package com.example.viacinema

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("http://www.via.dk")))
        }
    }
}
