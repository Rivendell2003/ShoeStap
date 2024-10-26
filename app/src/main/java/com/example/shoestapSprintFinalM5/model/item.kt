package com.example.shoestapSprintFinalM5.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Item(
    val name: String,
    val imageResId: Int,
    val price: Int,
    val description: String
) : Parcelable
