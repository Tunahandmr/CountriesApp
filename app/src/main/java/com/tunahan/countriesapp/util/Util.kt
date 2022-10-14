package com.tunahan.countriesapp.util

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.tunahan.countriesapp.R

    fun ImageView.downloadFromUrl(url:String?,progressDrawable: CircularProgressDrawable){

        val options = RequestOptions()
            .placeholder(progressDrawable)
            .error(R.mipmap.ic_launcher_round)

        Glide.with(context)
            .setDefaultRequestOptions(options)
            .load(url)
            .into(this)


    }

   fun placeHolderProgressBar(context:Context):CircularProgressDrawable{

        return CircularProgressDrawable(context).apply {
            strokeWidth = 8f
            centerRadius = 30f
            start()

        }


    }


@BindingAdapter("android:downloadUrl")
fun downloadImage(view: ImageView,url:String?){

    view.downloadFromUrl(url, placeHolderProgressBar(view.context))

}
