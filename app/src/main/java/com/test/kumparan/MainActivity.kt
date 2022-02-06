package com.test.kumparan

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.core.data.Resource
import com.test.core.domain.model.PostModel
import com.test.core.domain.model.UserModel
import com.test.core.ui.BaseActivity
import com.test.core.utils.toast
import com.test.kumparan.adapter.MainAdapter
import com.test.kumparan.databinding.ActivityMainBinding
import com.test.kumparan.viewmodel.PostViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val postViewModel: PostViewModel by viewModel()
    private lateinit var postAdapter: MainAdapter
    private var postList: List<PostModel>? = arrayListOf()
    private var userList: List<UserModel>? = arrayListOf()
    private var loadDataIsComplete = arrayOf(false, false)
    private var exit = false

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    override fun setup(savedInstanceState: Bundle?) {
        setupToolbar(
            R.id.toolbar,
            false,
            getString(R.string.label_main_menu),
            R.color.colorPrimary,
            4f
        )

        getResponse()
        setupRecyclerView()
        initOnClick()
    }

    private fun getResponse() {
        postAdapter = MainAdapter()
        postViewModel.post.observe(this, { post ->
            if (post != null) {
                when (post) {
                    is Resource.Loading -> {
                        binding.root.setBackgroundColor(ContextCompat.getColor(this, android.R.color.white))
                    }
                    is Resource.Success -> {
                        binding.shimmerLayout.visibility = View.GONE
                        binding.root.setBackgroundColor(ContextCompat.getColor(this, R.color.colorLine))
                        postList = post.data
                        loadDataIsComplete[0] = true
                        validateSetupRecyclerView()
                    }
                    is Resource.Error -> {
                        binding.shimmerLayout.visibility = View.GONE
                        binding.root.setBackgroundColor(ContextCompat.getColor(this, R.color.colorLine))
                        postList = post.data
                        loadDataIsComplete[0] = true
                        validateSetupRecyclerView()
                    }
                }
            }
        })

        postViewModel.user.observe(this, { user ->
            if (user != null) {
                when (user) {
                    is Resource.Loading -> {
                        binding.root.setBackgroundColor(ContextCompat.getColor(this, android.R.color.white))
                    }
                    is Resource.Success -> {
                        binding.shimmerLayout.visibility = View.GONE
                        binding.root.setBackgroundColor(ContextCompat.getColor(this, R.color.colorLine))
                        userList = user.data
                        loadDataIsComplete[1] = true
                        validateSetupRecyclerView()
                    }
                    is Resource.Error -> {
                        binding.shimmerLayout.visibility = View.GONE
                        binding.root.setBackgroundColor(ContextCompat.getColor(this, R.color.colorLine))
                        userList = user.data
                        loadDataIsComplete[1] = true
                        validateSetupRecyclerView()
                    }
                }
            }
        })
    }

    private fun setupRecyclerView() {
        with(binding.rvPostList) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = postAdapter
        }
    }

    private fun validateSetupRecyclerView() {
        if (loadDataIsComplete.all { it }) {
            for (user in userList!!) {
                for (post in postList!!) {
                    if (user.id == post.userId) {
                        post.name = user.name
                        post.company = user.companyName
                    }
                }
            }
            postAdapter.setData(postList)
        }
    }

    private fun initOnClick() {
        postAdapter.onItemClick = { selectedData, type ->
            if (type == MainAdapter.POST) {
                val intent = Intent(this, PostDetailActivity::class.java)
                intent.putExtra(PostDetailActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            } else {
                val intent = Intent(this, ProfileDetailActivity::class.java)
                intent.putExtra(PostDetailActivity.EXTRA_DATA, selectedData.userId)
                startActivity(intent)
            }
        }
    }

    override fun onBackPressed() {
        if (exit) super.onBackPressed() else {
            toast(getString(R.string.label_click_button_one_more_to_exit))
            exit = true
            Handler(Looper.getMainLooper()).postDelayed({ exit = false }, 3000)
        }
    }
}