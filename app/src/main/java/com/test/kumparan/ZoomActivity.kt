package com.test.kumparan

import android.os.Bundle
import android.transition.Transition
import android.view.LayoutInflater
import android.view.MenuItem
import com.squareup.picasso.Picasso
import com.test.core.domain.model.PhotoModel
import com.test.core.ui.BaseActivity
import com.test.core.utils.getCapsSentences
import com.test.kumparan.databinding.ActivityZoomBinding

class ZoomActivity : BaseActivity<ActivityZoomBinding>() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    override val bindingInflater: (LayoutInflater) -> ActivityZoomBinding
        get() = ActivityZoomBinding::inflate

    private var image: PhotoModel? = null

    override fun setup(savedInstanceState: Bundle?) {
        if (intent.hasExtra(EXTRA_DATA)) {
            image = intent.extras?.getParcelable(EXTRA_DATA)
        }

        setupToolbar(R.id.toolbar, true, getCapsSentences(image?.title.toString()), android.R.color.black, 0f)

        Picasso.get().load(image?.url).noFade().into(binding.ivImageDetail)

        //add transition listener to load full-size image on transition end
        window.sharedElementEnterTransition.addListener(object : Transition.TransitionListener {
            override fun onTransitionEnd(transition: Transition?) {
                //load full-size
                Picasso.get().load(image?.url).noFade().noPlaceholder().into(binding.ivImageDetail)
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
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}