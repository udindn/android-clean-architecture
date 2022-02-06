package com.test.kumparan

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.core.data.Resource
import com.test.core.domain.model.PostModel
import com.test.core.domain.model.UserModel
import com.test.core.ui.BaseActivity
import com.test.core.utils.getCapsSentences
import com.test.core.utils.loadAvatar
import com.test.kumparan.adapter.AlbumAdapter
import com.test.kumparan.adapter.CommentAdapter
import com.test.kumparan.adapter.MainAdapter
import com.test.kumparan.databinding.ActivityPostDetailBinding
import com.test.kumparan.databinding.ActivityProfileDetailBinding
import com.test.kumparan.viewmodel.PostViewModel
import com.test.kumparan.viewmodel.ProfileViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class ProfileDetailActivity : BaseActivity<ActivityProfileDetailBinding>()  {

    private var userId = 0
    private lateinit var albumAdapter: AlbumAdapter
    private val viewModel: ProfileViewModel by viewModel()

    override val bindingInflater: (LayoutInflater) -> ActivityProfileDetailBinding
        get() = ActivityProfileDetailBinding::inflate

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    override fun setup(savedInstanceState: Bundle?) {
        setupToolbar(
            R.id.toolbar,
            true,
            getString(R.string.label_profile),
            R.color.colorPrimary,
            4f
        )

        initExtras()
        setupRecyclerView()
        initOnClick()
    }

    private fun initExtras() {
        if (intent != null && intent.hasExtra(EXTRA_DATA)) {
            userId = intent.getIntExtra(EXTRA_DATA, 0)
            getResponse()
        }
    }

    private fun initView(user: UserModel) {
        with(binding) {
            etFullName.text = getCapsSentences(user.name)
            etAddressUser.text = getCapsSentences(user.address)
            etEmailUser.text = user.email
            etCompany.text = getCapsSentences(user.companyName)
            loadAvatar(null, ivAvatar, user.name)
        }
    }

    private fun setupRecyclerView() {
        albumAdapter = AlbumAdapter()
        with(binding.rvAlbum) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = albumAdapter
        }
    }

    private fun getResponse() {
        viewModel.deleteAlbum()
        viewModel.getAlbum(userId).observe(this, { album ->
            if (album != null) {
                when (album) {
                    is Resource.Loading -> {
                    }
                    is Resource.Success -> {
                        albumAdapter.setData(album.data)
                    }
                    is Resource.Error -> {
                        albumAdapter.setData(album.data)
                    }
                }
            }
        })

        viewModel.userFromDB.observe(this, { user ->
            if (user != null) {
               for (u in user) {
                   if (u.id == userId)
                       initView(u)
               }
            }
        })
    }

    private fun initOnClick() {
        albumAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, PhotoListActivity::class.java)
            intent.putExtra(PostDetailActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }

        return super.onOptionsItemSelected(item)
    }
}