package com.example.kennedycruiser.common

import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.example.kennedycruiser.R

fun FragmentActivity.updateError(errorMessage: String){
    Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
}

fun FragmentActivity.updateLoading(isLoading: Boolean){
    findViewById<ProgressBar>(R.id.progress_bar).apply {
        if (isLoading)
            visibility = View.VISIBLE
        else
            visibility = View.GONE
    }
}