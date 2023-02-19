package ru.vaimon.vkservices.screens.main

import ru.vaimon.vkservices.models.VKService
import ru.vaimon.vkservices.screens.main.adapters.VkServicesRecyclerViewAdapter

interface MainContract {
    interface View{

        fun displayError(message: String)
        fun toggleProgressBar(isProcessRunning: Boolean)
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