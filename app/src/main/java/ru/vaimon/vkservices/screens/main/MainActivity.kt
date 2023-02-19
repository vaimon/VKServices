package ru.vaimon.vkservices.screens.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import ru.vaimon.vkservices.R
import ru.vaimon.vkservices.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var binding: ActivityMainBinding
    private val mPresenter : MainContract.Presenter by lazy{
        MainPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
        mPresenter.fetchVkServices()
    }

    override fun onStart() {
        super.onStart()
        mPresenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        mPresenter.detachView()
    }

    private fun setupUI() {
        binding.rvVKServices.apply {
            adapter = mPresenter.vkServicesRecyclerViewAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun toggleProgressBar(isProcessRunning: Boolean){
        binding.isLoading = isProcessRunning
    }

    override fun displayError(message: String){
        Toast.makeText(applicationContext,message,Toast.LENGTH_LONG).show()
    }
}