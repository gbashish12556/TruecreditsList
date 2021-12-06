package com.example.truecreditslist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.viewModels
import com.example.truecreditslist.R
import com.example.truecreditslist.databinding.ActivityMainBinding
import com.example.truecreditslist.getViewModelFactory
import com.example.truecreditslist.ui.screens.TruecreditPagingAdapter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_main)
    }


}