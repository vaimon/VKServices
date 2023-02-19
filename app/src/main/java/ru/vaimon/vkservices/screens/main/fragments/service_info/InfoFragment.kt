package ru.vaimon.vkservices.screens.main.fragments.service_info

import android.R.attr.data
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.squareup.picasso.Picasso
import ru.vaimon.vkservices.R
import ru.vaimon.vkservices.databinding.FragmentInfoBinding
import ru.vaimon.vkservices.models.VKService


class InfoFragment(
    val service: VKService
) : BottomSheetDialogFragment(), InfoContract.View {

    private val mPresenter: InfoContract.Presenter by lazy{
        InfoPresenter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentInfoBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_info, container, false
        )
        binding.serviceName = service.name
        binding.serviceDescription = service.description
        binding.serviceUrl = service.serviceUrl
        Picasso.with(context)
            .load(service.iconUrl)
            .into(binding.ivServiceIcon);
        setupUI(binding)
        return binding.getRoot()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mPresenter.attachView(this)
    }

    override fun onDetach() {
        super.onDetach()
        mPresenter.detachView()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }

    private fun setupUI(binding: FragmentInfoBinding) {
        setUpListeners(binding)
    }

    private fun setUpListeners(binding: FragmentInfoBinding) {
        binding.toolbar.setNavigationOnClickListener {
            dismiss()
        }
    }
}