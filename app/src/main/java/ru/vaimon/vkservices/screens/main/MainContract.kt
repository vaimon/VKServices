package ru.vaimon.vkservices.screens.main

import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.vaimon.vkservices.models.VKService
import ru.vaimon.vkservices.screens.main.adapters.VkServicesRecyclerViewAdapter

interface MainContract {
    interface View{

        fun displayError(message: String)
        fun toggleProgressBar(isProcessRunning: Boolean)
        fun showBottomDialog(fragment: BottomSheetDialogFragment)
    }

    interface Presenter{
        val vkServicesRecyclerViewAdapter: VkServicesRecyclerViewAdapter
        fun attachView(view: View)
        fun detachView()

        fun onServicesRequestError(message: String)
        fun onVkServicesFetched(services: List<VKService>?)
        fun fetchVkServices()
    }

    interface Repository{

        fun fetchVkServicesInfo()
    }
}