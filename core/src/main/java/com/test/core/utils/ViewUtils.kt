package com.test.core.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import com.arrayyan.context.FastApp
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun loadAvatar(url: String?, imageView: ImageView, name: String?) {
    if (url.isNullOrEmpty()) {
        imageView.setImageBitmap(LetterTile.instance.getLetterTitle(name))
    } else {
        Glide.with(FastApp.getAppContext())
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .dontAnimate()
            .let { request ->
                if (imageView.drawable != null) {
                    request.placeholder(imageView.drawable.constantState?.newDrawable()?.mutate())
                } else {
                    request
                }
            }
            .into(imageView)
    }
}

fun loadImage(context: Context, url: String?, imageView: ImageView, placeholder: Int, progressBar: View) {
    if (url == null || url.isEmpty()) {
        progressBar.visibility = View.GONE
    } else {
        progressBar.visibility = View.VISIBLE
        Glide.with(context)
                .load(url)
                .placeholder(placeholder)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                    ): Boolean {
                        progressBar.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                    ): Boolean {
                        progressBar.visibility = View.GONE
                        return false
                    }
                })
                .into(imageView)
    }
}

fun loadImage(context: Context, url: String?, imageView: ImageView) {
    Glide.with(context)
        .load(url)
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
            ): Boolean {
                return false
            }

            override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
            ): Boolean {
                return false
            }
        })
        .into(imageView)
}

fun parseDateToYear(times: String): String? {
    val inputPattern = "yyyy-MM-dd"
    val inputFormat = SimpleDateFormat(inputPattern, Locale("en", "EN"))
    var time: String? = null
    try {
        val cal = inputFormat.parse(times)
        val format = "yyyy"
        val sdf = SimpleDateFormat(format, Locale("en", "EN"))
        time = sdf.format(cal!!)
        return time
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return time
}

fun setPopupMenu(
        context: Context,
        view: View,
        menu: Int,
        item: (item: MenuItem) -> Unit
) {
    val popup = PopupMenu(context, view)
    popup.menuInflater.inflate(menu, popup.menu)
    popup.setOnMenuItemClickListener { items ->
        item(items)
        true
    }
    popup.show()
}

fun getCapsSentences(tagName: String): String {
    val splits = tagName.lowercase(Locale("id", "ID")).split(" ".toRegex())
        .dropLastWhile { it.isEmpty() }.toTypedArray()
    val sb = StringBuilder()
    for (i in splits.indices) {
        val eachWord = splits[i]
        if (i > 0 && eachWord.isNotEmpty()) {
            sb.append(" ")
        }
        val cap =
            eachWord.substring(0, 1).uppercase(Locale("id", "ID")) + eachWord.substring(1)
        sb.append(cap)
    }
    return sb.toString()
}