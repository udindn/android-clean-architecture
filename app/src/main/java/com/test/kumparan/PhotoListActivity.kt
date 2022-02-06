package com.test.kumparan

import android.content.Intent
import android.content.IntentFilter.create
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.test.core.data.Resource
import com.test.core.domain.model.AlbumModel
import com.test.core.ui.BaseActivity
import com.test.kumparan.adapter.PhotoAdapter
import com.test.kumparan.databinding.ActivityPhotoListBinding
import com.test.kumparan.databinding.ActivityPostDetailBinding
import com.test.kumparan.viewmodel.ProfileViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import java.net.URI.create

class PhotoListActivity : BaseActivity<ActivityPhotoListBinding>() {

    private val viewModel: ProfileViewModel by viewModel()
    private lateinit var photoAdapter: PhotoAdapter
    private lateinit var albumModel: AlbumModel

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    override val bindingInflater: (LayoutInflater) -> ActivityPhotoListBinding
        get() = ActivityPhotoListBinding::inflate

    override fun setup(savedInstanceState: Bundle?) {
        setupToolbar(
            R.id.toolbar,
            true,
            getString(R.string.label_photo_list),
            R.color.colorPrimary,
            4f
        )
        initOnClick()
        setupRecyclerView()
        initExtras()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun initExtras() {
        if (intent != null && intent.hasExtra(EXTRA_DATA)) {
            albumModel = intent.getParcelableExtra(EXTRA_DATA)!!
            getResponse(albumModel.id)
        }
    }

    private fun setupRecyclerView() {
        binding.rvPhotoList.apply {
            layoutManager =
                StaggeredGridLayoutManager(
                    2,
                    StaggeredGridLayoutManager.VERTICAL
                )
            adapter = photoAdapter
        }
    }

    private fun getResponse(albumId: Int) {
        viewModel.deletePhoto()
        viewModel.getPhoto(albumId).observe(this, { photo ->
            if (photo != null) {
                when (photo) {
                    is Resource.Loading -> {
                    }
                    is Resource.Success -> {
                        photoAdapter.setData(photo.data)
                        binding.pbLoadListPhoto.visibility = View.GONE
                    }
                    is Resource.Error -> {
                        photoAdapter.setData(photo.data)
                        binding.pbLoadListPhoto.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun initOnClick() {
        photoAdapter = PhotoAdapter()
        photoAdapter.onItemClick = { selectData, imageView ->
            val detailIntent = Intent(this, PhotoDetailActivity::class.java)
            val imageViewPair = Pair<View, String>(imageView, "ImageTransition")
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, imageViewPair)

            detailIntent.putExtra(EXTRA_DATA, selectData) // pass your bundle data
            startActivity(detailIntent, options.toBundle())
        }
    }
}