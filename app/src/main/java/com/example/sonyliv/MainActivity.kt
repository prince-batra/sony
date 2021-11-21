package com.example.sonyliv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.sonyliv.databinding.ActivityMainBinding
import com.example.sonyliv.model.LocalizationResponse
import com.example.sonyliv.viewmodels.LocalizationViewModel
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        init()
    }

    private fun init() {
        val viewModel: LocalizationViewModel = ViewModelProvider(this@MainActivity).get(LocalizationViewModel::class.java)
        binding.apply {
            lifecycleOwner = this@MainActivity
            callback = viewModel
            response = LocalizationResponse("")
        }
        viewModel.init()
        viewModel.dataSource.observe(this) {
            if (it == null) {
                binding.response = LocalizationResponse(resources.getString(R.string.hello_world))
                showSnackBarError()
            } else
                binding.response = it
        }
    }

    private fun showSnackBarError() {
        Snackbar.make(
            binding.root, resources.getString(R.string.internet_error),
            Snackbar.LENGTH_LONG
        ).show()
    }

}