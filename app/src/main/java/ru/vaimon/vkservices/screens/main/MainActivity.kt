package ru.vaimon.vkservices.screens.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.vaimon.vkservices.databinding.ActivityMainBinding
import ru.vaimon.vkservices.screens.main.fragments.error_alert.ErrorAlertFragment

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

        binding.btnRetry.setOnClickListener {
            mPresenter.onRetryButtonClicked()
        }
    }

    override fun toggleProgressBar(isProcessRunning: Boolean){
        binding.isLoading = isProcessRunning
    }

    override fun displayError(messageResId: Int){
        ErrorAlertFragment(messageResId).show(
            supportFragmentManager, ErrorAlertFragment.TAG)
    }

    override fun showBottomDialog(fragment: BottomSheetDialogFragment) {
        fragment.show(supportFragmentManager, fragment.tag)
    }

    override fun toggleRetryButton(isVisible: Boolean) {
        binding.isWorthRetrying = isVisible
    }
}