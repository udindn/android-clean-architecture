package com.test.kumparan

import android.content.Intent
import android.os.Bundle
import android.transition.Transition
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import com.squareup.picasso.Picasso
import com.test.core.domain.model.PhotoModel
import com.test.core.ui.BaseActivity
import com.test.core.utils.getCapsSentences
import com.test.kumparan.databinding.ActivityPhotoDetailBinding
import kotlinx.android.synthetic.main.activity_photo_detail.*

class PhotoDetailActivity : BaseActivity<ActivityPhotoDetailBinding>() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private lateinit var photoModel: PhotoModel

    override val bindingInflater: (LayoutInflater) -> ActivityPhotoDetailBinding
        get() = ActivityPhotoDetailBinding::inflate

    override fun setup(savedInstanceState: Bundle?) {
        initExtras()

        Picasso.get().load(photoModel.url).noFade().into(binding.ivPhoto)
        binding.tvPhotoName.text = getCapsSentences(photoModel.title)
//
//        //add transition listener to load full-size image on transition end
        window.sharedElementEnterTransition.addListener(object : Transition.TransitionListener {
            override fun onTransitionEnd(transition: Transition?) {
                //load full-size
                Picasso.get().load(photoModel.url).noFade().noPlaceholder().into(binding.ivPhoto)
                transition?.removeListener(this)
            }

            override fun onTransitionResume(transition: Transition?) {
                //To change body of created functions use File | Settings | File Templates.
            }

            override fun onTransitionPause(transition: Transition?) {
                //To change body of created functions use File | Settings | File Templates.
            }

            override fun onTransitionCancel(transition: Transition?) {
                //To change body of created functions use File | Settings | File Templates.
            }

            override fun onTransitionStart(transition: Transition?) {
                //To change body of created functions use File | Settings | File Templates.
            }
        })

        setupToolbar(
            R.id.toolbar,
            true,
            photoModel.title,
            R.color.colorRed,
            4f
        )
        initOnClick()
    }

    private fun initExtras() {
        if (intent != null && intent.hasExtra(EXTRA_DATA)) {
            photoModel = intent.getParcelableExtra(EXTRA_DATA)!!
        }
    }

    private fun initOnClick() {
        binding.view.setOnClickListener {
            supportFinishAfterTransition()
        }

        binding.ivPhoto.setOnClickListener {
            val detailIntent = Intent(this, ZoomActivity::class.java)
            val imageViewPair = Pair<View, String>(binding.ivPhoto, "ImageTransition")
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, imageViewPair)

            detailIntent.putExtra(EXTRA_DATA, photoModel) // pass your bundle data
            startActivity(detailIntent, options.toBundle())
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                supportFinishAfterTransition()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}