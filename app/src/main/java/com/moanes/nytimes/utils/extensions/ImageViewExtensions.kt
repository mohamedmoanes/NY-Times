package com.moanes.nytimes.utils.extensions

import androidx.appcompat.widget.AppCompatImageView
import com.moanes.nytimes.R
import com.squareup.picasso.Picasso

fun AppCompatImageView.setImageURL(url: String, placeholder: Int= R.drawable.ic_image_placeholder) {
    Picasso.get().load(url).fit().placeholder(placeholder).into(this)
}