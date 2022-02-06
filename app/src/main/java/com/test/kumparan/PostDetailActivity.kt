package com.test.kumparan

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.core.data.Resource
import com.test.core.domain.model.PostModel
import com.test.core.ui.BaseActivity
import com.test.core.utils.getCapsSentences
import com.test.core.utils.loadAvatar
import com.test.kumparan.adapter.CommentAdapter
import com.test.kumparan.adapter.MainAdapter
import com.test.kumparan.databinding.ActivityPostDetailBinding
import com.test.kumparan.viewmodel.PostViewModel
import com.test.kumparan.viewmodel.ProfileViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class PostDetailActivity : BaseActivity<ActivityPostDetailBinding>() {

    private lateinit var postModel: PostModel
    private lateinit var commentAdapter: CommentAdapter
    private val postViewModel: PostViewModel by viewModel()

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    override val bindingInflater: (LayoutInflater) -> ActivityPostDetailBinding
        get() = ActivityPostDetailBinding::inflate

    override fun setup(savedInstanceState: Bundle?) {
        setupToolbar(
            R.id.toolbar,
            true,
            getString(R.string.label_detail),
            R.color.colorPrimary,
            4f
        )

        initExtras()
        setupRecyclerView()
        initOnClick()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun initExtras() {
        if (intent != null && intent.hasExtra(EXTRA_DATA)) {
            postModel = intent.getParcelableExtra(EXTRA_DATA)!!
            initView(postModel)
            getResponse(postModel.id)
        }
    }

    private fun initView(post: PostModel) {
        with(binding) {
            tvTitle.text = getCapsSentences(post.title.toString())
            tvUserName.text = getCapsSentences(post.name.toString())
            tvBody.text = post.body
            loadAvatar(null, ivUser, post.name)
        }
    }

    private fun setupRecyclerView() {
        commentAdapter = CommentAdapter()
        with(binding.rvCommentList) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = commentAdapter
        }
    }

    private fun getResponse(postId: Int) {
        postViewModel.deleteComment()
        postViewModel.getResponse(postId).observe(this, { comment ->
            if (comment != null) {
                when (comment) {
                    is Resource.Loading -> {
                    }
                    is Resource.Success -> {
                        commentAdapter.setData(comment.data)
                    }
                    is Resource.Error -> {
                        commentAdapter.setData(comment.data)
                    }
                }
            }
        })
    }

    private fun initOnClick() {
        commentAdapter.onItemClick = { _ ->
            val intent = Intent(this, ProfileDetailActivity::class.java)
            intent.putExtra(EXTRA_DATA, postModel.userId)
            startActivity(intent)
        }
    }
}