package com.example.truecreditslist

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment


fun Fragment.getViewModelFactory(): ViewModelFactory {
    val repository = (requireContext().applicationContext as TruecreditsApplication).prRepository
    return ViewModelFactory(repository, this)
}

fun AppCompatActivity.getViewModelFactory(): ViewModelFactory {
    val repository = (applicationContext as TruecreditsApplication).prRepository
    return ViewModelFactory(repository, this)
}