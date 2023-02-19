package ru.vaimon.vkservices.screens.main

import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.vaimon.vkservices.models.VKService
import ru.vaimon.vkservices.screens.main.adapters.VkServicesRecyclerViewAdapter

interface MainContract {
    interface View{

        fun displayError(messageResId: Int)
        fun toggleProgressBar(isProcessRunning: Boolean)
        fun showBottomDialog(fragment: BottomSheetDialogFragment)
    }

    interface Presenter{
        val vkServicesRecyclerViewAdapter: VkServicesRecyclerViewAdapter
        fun attachView(view: View)
        fun detachView()
        fun onVkServicesFetched(services: List<VKService>?)
        fun fetchVkServices()
        fun onServicesRequestError(e: Throwable)
    }

    interface Repository{

        fun fetchVkServicesInfo()
    }
}