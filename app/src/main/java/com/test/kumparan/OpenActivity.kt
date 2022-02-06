package com.test.kumparan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.lifecycleScope
import com.test.core.ui.BaseActivity
import com.test.kumparan.databinding.ActivityOpenBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class OpenActivity : BaseActivity<ActivityOpenBinding>() {

    override val bindingInflater: (LayoutInflater) -> ActivityOpenBinding
        get() = ActivityOpenBinding::inflate

    override fun setup(savedInstanceState: Bundle?) {
        lifecycleScope.launch {
            delay(5000)
            val intent = Intent(this@OpenActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}