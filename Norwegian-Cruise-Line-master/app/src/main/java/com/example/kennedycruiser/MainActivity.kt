package com.example.kennedycruiser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kennedycruiser.view.ShipFleetFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null)
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ShipFleetFragment())
                .commit()
    }
}